/*
 * Copyright (c) 2023. Created by Andras Laczo.
 */

import React, {useEffect, useState} from 'react';
import {Button, ButtonGroup, CardFooter, Container, Table} from 'reactstrap';
import AppNavbar from './AppNavbar';
import {Link} from 'react-router-dom';
import {useCookies} from 'react-cookie';
import {Player} from "@lottiefiles/react-lottie-player";
import "./TeamList.css"
import AppFooter from "./AppFooter";

const TeamList = (props) => {

    const [cookies] = useCookies(['XSRF-TOKEN']);
    const [teams, setTeams] = useState([]);
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        setLoading(true);

        fetch('api/formulaoneteams')
            .then(response => response.json())
            .then(data => {
                setTeams(data);
                setLoading(false);
            })
    }, [setTeams, setLoading]);

    const remove = async (id) => {
        await fetch(`/api/formulaoneteam/${id}`, {
            method: 'DELETE',
            headers: {
                'X-XSRF-TOKEN': cookies['XSRF-TOKEN'],
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            credentials: 'include'
        }).then(() => {
            let updatedTeams = [...teams].filter(i => i.id !== id);
            setTeams(updatedTeams);
        });
    }

    if (loading) {
        return <p>Loading...</p>;
    }

    const teamListSecuredButtons = team => {
        return <td>
            <ButtonGroup>
                <Button size="sm" color="secondary" tag={Link} to={"/teams/" + team.id}>Edit</Button>
                <Button size="sm" color="danger" onClick={() => remove(team.id)}>Delete</Button>
            </ButtonGroup>
        </td>
    }

    const teamList = teams.map(team => {
        return <tr key={team.id}>
            <td style={{whiteSpace: 'nowrap'}}>{team.name}</td>
            <td>{team.foundationYear}</td>
            <td>{team.worldChampionshipsWon}</td>
            <td>{team.entryFeePaid && 'X'}</td>
            {props.authenticated ? teamListSecuredButtons(team) : ""}
        </tr>
    });

    return (
        <div className="team-wrapper">
            <div className="team-content">
                <AppNavbar authenticated={props.authenticated}/>
                <Container fluid>
                    <Player className="lottie-flag-player"
                            autoplay
                            speed={0.6}
                            keepLastFrame={true}
                            src="https://assets4.lottiefiles.com/packages/lf20_lfuo4vnm.json"
                            style={{height: '100px', width: '100px'}}
                    >
                    </Player>
                    <Table>
                        <thead>
                        <tr>
                            <th width="30%">Name</th>
                            <th width="20%">Founded in</th>
                            <th width="10%">
                                <Player className="lottie-champ-player"
                                    autoplay
                                    speed={1}
                                    keepLastFrame={true}
                                    src="https://assets6.lottiefiles.com/packages/lf20_qsykmyhi.json"
                                    style={{height: '50px', width: '50px'}}
                                >
                                </Player>
                            </th>
                            <th width="20%">Entry-fee paid</th>
                            {props.authenticated ? <th width="20%">Actions</th> : ""}
                        </tr>
                        </thead>
                        <tbody>
                        {teamList}
                        </tbody>
                    </Table>
                </Container>
            </div>
            <AppFooter/>
        </div>
    );
};

export default TeamList;