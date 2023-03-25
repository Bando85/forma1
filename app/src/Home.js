/*
 * Copyright (c) 2023. Created by Andras Laczo.
 */

import React from 'react';
import './App.css';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';
import { Button, Container } from 'reactstrap';
import TeamList from "./TeamList";

const Home = () => {
    return (
        <div>
            <AppNavbar/>
            <Container fluid>

            </Container>
        </div>
    );
}

export default Home;