import React, {useEffect, useState} from 'react';
import './App.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import TeamList from './TeamList';
import TeamEdit from './TeamEdit';
import Login from "./Login";

const App = () => {

    const [authenticated, setAuthenticated] = useState(false);

    useEffect(() => {
        fetch('api/user')
            .then(response => response.text())
            .then(body => {
                if (body === 'authenticated') {
                    setAuthenticated(true);
                } else {
                    setAuthenticated(false);
                }
            })
    }, [setAuthenticated]);

    return (
        <Router>
            <Routes>
                <Route exact path="/login" element={<Login/>}/>
                <Route exact path="/" element={<Login/>}/>
                <Route path='/teams' exact={true} element={<TeamList authenticated={authenticated}/>}/>
                <Route path='/teams/:id' element={<TeamEdit authenticated={authenticated}/>}/>
            </Routes>
        </Router>
    )
}

export default App;

