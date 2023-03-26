/*
 * Copyright (c) 2023. Created by Andras Laczo.
 */

import React, { useEffect, useState } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';
import { useCookies } from 'react-cookie';

const TeamList = () => {

    const [cookies] = useCookies(['XSRF-TOKEN']);
    const [authenticated, setAuthenticated] = useState(false);
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


    useEffect(() => {
        setLoading(true);
        fetch('api/user')
            .then(response => response.text())
            .then(body => {
                console.log(body)
                if (body === 'authenticated') {
                    setAuthenticated(true);
                } else {
                    setAuthenticated(false);
                }
                setLoading(false);
            })
    },[setAuthenticated, setLoading]);


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
            {authenticated ? teamListSecuredButtons(team) : ""}
        </tr>
    });


    return (
        <div>
            <AppNavbar authenticated={authenticated}/>
            <Container fluid>
                <div className="float-end">
                    {authenticated ? <Button color="primary" tag={Link} to="/teams/new">Add Team</Button> : ''}
                </div>
                <h3>Formula One Teams</h3>
                <Table className="mt-4">
                    <thead>
                    <tr>
                        <th width="30%">Name</th>
                        <th width="10%">Founded in</th>
                        <th width="10%">World Champion <br/> (x times)</th>
                        <th width="10%">Entry-fee paid</th>
                        {authenticated ? <th width="10%">Actions</th> :"" }
                    </tr>
                    </thead>
                    <tbody>
                    {teamList}
                    </tbody>
                </Table>
            </Container>
        </div>
    );
};

export default TeamList;