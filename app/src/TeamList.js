/*
 * Copyright (c) 2023. Created by Andras Laczo.
 */

import React, { useEffect, useState } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';

const TeamList = () => {

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
    }, []);

    const remove = async (id) => {
        await fetch(`/api/formulaoneteam/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedTeams = [...teams].filter(i => i.id !== id);
            setTeams(updatedTeams);
        });
    }

    if (loading) {
        return <p>Loading...</p>;
    }

    const teamList = teams.map(team => {
        return <tr key={team.id}>
            <td style={{whiteSpace: 'nowrap'}}>{team.name}</td>
            <td>{team.foundationYear}</td>
            <td>{team.worldChampionshipsWon}</td>
            <td>{team.entryFeePaid && 'X'}</td>
            <td>
                <ButtonGroup>
                    <Button size="sm" color="secondary" tag={Link} to={"/teams/" + team.id}>Edit</Button>
                    <Button size="sm" color="danger" onClick={() => remove(team.id)}>Delete</Button>
                </ButtonGroup>
            </td>
        </tr>
    });

    return (
        <div>
            <AppNavbar/>
            <Container fluid>
                <div className="float-end">
                    <Button color="primary" tag={Link} to="/teams/new">Add Team</Button>
                </div>
                <h3>Formula One Teams</h3>
                <Table className="mt-4">
                    <thead>
                    <tr>
                        <th width="30%">Name</th>
                        <th width="10%">Founded in</th>
                        <th width="10%">World Champion <br/> (x times)</th>
                        <th width="10%">Entry-fee paid</th>
                        <th width="10%">Actions</th>
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