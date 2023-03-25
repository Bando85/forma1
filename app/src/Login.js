/*
 * Copyright (c) 2023. Created by Andras Laczo.
 */

import React, {useState} from 'react';
import './login.css';

const Login = () => {

    return (
            <div className="wrapper">
                <div className="container main">
                    <div className="row">
                        <div className="col-md-6 side-image">
                            <div className="text">
                                <p>Formula One Teams</p>
                            </div>
                        </div>
                        <div className="col-md-6 right">
                            <div className="input-box">
                                <header>Please sign in</header>
                                <div className="input-field">
                                    <input type="text" className="input" id="email" required autoComplete="off"/>
                                    <label htmlFor="email">Email</label>
                                </div>
                                <div className="input-field">
                                    <input type="password" className="input" id="password" required/>
                                    <label htmlFor="password">Password</label>
                                </div>
                                <div className="input-field">
                                    <input type="submit" className="submit" value="Sign in"/>
                                </div>
                                <div className="signin">
                                    <span>Don't have an account? <a href="/teams">Sign in as guest</a></span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
    );
};

export default Login;