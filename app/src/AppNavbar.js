/*
 * Copyright (c) 2023. Created by Andras Laczo.
 */

import React, {useEffect, useState} from 'react';
import {Collapse, Nav, Navbar, NavbarBrand, NavbarToggler, NavItem, NavLink} from 'reactstrap';
import {Link} from 'react-router-dom';
import {useCookies} from 'react-cookie';

const AppNavbar = (props) => {

    const [cookies] = useCookies(['XSRF-TOKEN']);
    const [isOpen, setIsOpen] = useState(false);

    const logout = () => {
        fetch('/logout', {
            method: 'POST', credentials: 'include',
            headers: {'X-XSRF-TOKEN': cookies['XSRF-TOKEN']}
        });
        window.location.href = window.location.origin
    }

    return (
        <Navbar color="dark" dark expand="md">
            <NavbarBrand tag={Link} to="/teams">Formula One Teams</NavbarBrand>
            <NavbarToggler onClick={() => {
                setIsOpen(!isOpen)
            }}/>
            <Collapse isOpen={isOpen} navbar>
                <Nav className="justify-content-between" style={{width: "100%"}} navbar>
                    <NavItem>
                        {props.authenticated ? <NavLink href="/" onClick={logout}>Logout</NavLink> :
                            <NavLink href="/login">Login</NavLink>}
                    </NavItem>
                    <NavItem>
                        {props.authenticated ?
                            <NavLink tag={Link} to="/teams/new"><strong>Add team</strong></NavLink> : ''}
                    </NavItem>
                    <NavItem>
                        <NavLink href="https://github.com/Bando85">GitHub</NavLink>
                    </NavItem>
                </Nav>
            </Collapse>
        </Navbar>

    );
};


export default AppNavbar;