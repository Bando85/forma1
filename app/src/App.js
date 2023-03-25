import React from 'react';
import './App.css';
import Home from './Home';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import TeamList from './TeamList';
import TeamEdit from './TeamEdit';
import Login from "./Login";

const App = () => {
    return (
        <Router>
            <Routes>
                <Route exact path="/login" element={<Login/>}/>
                <Route exact path="/" element={<Login/>}/>
                <Route path='/teams' exact={true} element={<TeamList/>}/>
                <Route path='/teams/:id' element={<TeamEdit/>}/>
            </Routes>
        </Router>
    )
}

export default App;

