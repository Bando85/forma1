/*
 * Copyright (c) 2023. Created by Andras Laczo.
 */

import React, {useState} from 'react';
import './login.css';
import { useCookies } from 'react-cookie';

const Login = () => {

    const [cookies] = useCookies(['XSRF-TOKEN']);
    const error = window.location.search.includes("error");

    return (
        <div className="login-wrapper">
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
                            {error ? <div className="login-error-message">Wrong username or password!</div>:<div><br/></div>}
                            <form action="/login" method="post">
                                <div className="input-field">
                                    <input type="text" className="input" id="username" name="username" required autoComplete="off"/>
                                    <label htmlFor="username">Username</label>
                                </div>
                                <div className="input-field">
                                    <input type="password" className="input" id="password" name="password" required/>
                                    <label htmlFor="password">Password</label>
                                </div>
                                <div className="input-field">
                                    <input type="submit" className="submit" value="Sign in"/>
                                </div>
                                <input type="hidden" name="_csrf" value={cookies['XSRF-TOKEN']}/>

                            </form>
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