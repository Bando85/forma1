import React, {useEffect, useState} from 'react';
import {Link, useNavigate, useParams} from 'react-router-dom';
import {Button, Container, Form, FormGroup, Input, Label} from 'reactstrap';
import AppNavbar from './AppNavbar';
import AppFooter from "./AppFooter";
import './TeamEdit.css';
import {useCookies} from 'react-cookie';


const TeamEdit = (props) => {

    const initialFormState = {
        name: '',
        foundationYear: '',
        worldChampionshipsWon: '',
        entryFeePaid: false
    };
    const [cookies] = useCookies(['XSRF-TOKEN']);
    const [team, setTeam] = useState(initialFormState);
    const navigate = useNavigate();
    const {id} = useParams();
    const errorMessageName = "Please use 1-20 characters and only letters"
    const errorMessagPositiveNumber = "Must be a positiv integer"

    useEffect(() => {
        if (id !== 'new') {
            fetch(`/api/formulaoneteam/${id}`)
                .then(response => response.json())
                .then(data => setTeam(data));
        }
    }, [id, setTeam]);

    const handleChange = (event) => {
        let {name, value} = event.target
        setTeam({...team, [name]: value})
    }
    const handleCheckBoxChange = (event) => {
        let {name, checked} = event.target
        setTeam({...team, [name]: checked})
    }

    const handleSubmit = async (event) => {
        event.preventDefault();

        await fetch(`/api/formulaoneteam${team.id ? `/${team.id}` : ''}`, {
            method: (team.id) ? 'PUT' : 'POST',
            headers: {
                'X-XSRF-TOKEN': cookies['XSRF-TOKEN'],
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(team),
            credentials: 'include'
        });
        setTeam(initialFormState);
        navigate('/teams');
    }

    const title = <h2 className="team-edit-header">{team.id ? 'Edit Team' : 'Add Team'}</h2>;

    return (
        <div className="team-wrapper">
            <div className="team-content">
                <AppNavbar authenticated={props.authenticated}/>
                <Container>
                    {title}
                    <Form onSubmit={handleSubmit}>
                        <FormGroup>
                            <Label for="name">Name</Label>
                            <Input autoFocus={true} type="text" name="name" id="name" value={team.name || ''}
                                   onChange={handleChange} required={true} pattern='^[A-Za-z]{1,20}$'
                                   autoComplete="name"/>
                            <div className="form-error-message">
                                <span>{errorMessageName}</span>
                            </div>
                        </FormGroup>
                        <FormGroup>
                            <Label for="foundationYear">Foundation Year</Label>
                            <Input type="number" name="foundationYear" id="foundationYear"
                                   value={team.foundationYear || ''}
                                   onChange={handleChange} min="0" autoComplete="foundationYear"/>
                            <div className="form-error-message">
                                <span>{errorMessagPositiveNumber}</span>
                            </div>
                        </FormGroup>
                        <FormGroup>
                            <Label for="worldChampionshipsWon">Championships won</Label>
                            <Input type="number" name="worldChampionshipsWon" id="worldChampionshipsWon"
                                   value={team.worldChampionshipsWon || ''} min="0"
                                   onChange={handleChange} autoComplete="worldChampionshipsWon"/>
                            <div className="form-error-message">
                                <span>{errorMessagPositiveNumber}</span>
                            </div>
                        </FormGroup>
                        <FormGroup>
                            <Label for="entryFeePaid">
                                Entry-fee paid
                                <br/>
                                <Input type="checkbox" name="entryFeePaid" id="entryFeePaid"
                                       checked={team.entryFeePaid || false}
                                       onChange={handleCheckBoxChange}/>
                            </Label>
                        </FormGroup>

                        <FormGroup>
                            <Button color="primary" type="submit">Save</Button>{' '}
                            <Button color="secondary" tag={Link} to="/teams">Cancel</Button>
                        </FormGroup>
                    </Form>
                </Container>

            </div>
            <AppFooter/>
        </div>
    )
};

export default TeamEdit;