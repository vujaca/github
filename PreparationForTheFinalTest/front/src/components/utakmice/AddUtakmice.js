import React, { useState, useEffect, useCallback } from 'react';
import { Row, Col, Form, Button } from "react-bootstrap";
import { useNavigate } from 'react-router-dom';
import Axios from '../../apis/Axios';
import { withNavigation } from '../../routeconf';

function AddUtakmice(props) {

    const empty_utakmica = {
        reprezentacijaAId: "",
        reprezentacijaBId: ""
    }
    const [utakmica, setUtakmica] = useState(empty_utakmica)
    const [reprezentacije, setReprezentacije] = useState([])
    const [igraci, setIgraci] = useState([])
    const [isValid, setIsValid] = useState(false)
    const navigate = useNavigate()

    useEffect(() => {
        getReprezentacije()
        getIgraci()
    }, [])

    const getReprezentacije = useCallback(() => {
        Axios.get("/reprezentacije")
        .then(res => {
            console.log(res.data)
            setReprezentacije(res.data)
        })
        .catch(error => {
            console.log(error)
            alert('Greska prilikom preuzimanja reprezentacija')
        })
    }, [])

    const getIgraci = useCallback(() => {
        Axios.get("/igraci")
        .then(res => {
            console.log(res.data)
            setIgraci(res.data)
        })
        .catch(error => {
            console.log(error)
            alert('Greska prilikom preuzimanja igraca')
        })
    }, [])


    const valueInputChanged = (e) => {
        let input = e.target;

        let name = input.name;
        let value = input.value;

        setUtakmica(utakmica => {
            utakmica[name] = value
            console.log(utakmica)
            return utakmica
        });
        isInputValid()
    }

    const reprezentacijaASelectionChanged = (e) => {
        let reprezentacijaAId = e.target.value;
        let reprezentacijaA = reprezentacije.find((reprezentacija) => reprezentacija.id == reprezentacijaAId);

        setUtakmica(utakmica => {
            utakmica.reprezentacijaA = reprezentacijaA
            return utakmica
        });
        isInputValid()
    }

    const reprezentacijaBSelectionChanged = (e) => {
        let reprezentacijaBId = e.target.value;
        let reprezentacijaB = reprezentacije.find((reprezentacija) => reprezentacija.id == reprezentacijaBId);

        setUtakmica(utakmica => {
            utakmica.reprezentacijaB = reprezentacijaB;
            return utakmica
        });
        isInputValid()
    }

    const create = () => {
        let utakmicaDTO = {
            reprezentacijaA: utakmica.reprezentacijaA,
            reprezentacijaB: utakmica.reprezentacijaB,
        }

        Axios.post("/utakmice", utakmicaDTO)
        .then(() => {
            navigate('/utakmice');
        })
        .catch(error => {
            console.log(error)
            alert('Error while creating utakmice')
        })
    }

    const field_empty = (key) => {
        const value = utakmica[key]
        return (value == null || value == "" || value == 0)
    }

    const isInputValid = () => {
        //proveravamo da li je bilo koje polje ostalo prazno
        for (const key in utakmica) {
            if (utakmica.hasOwnProperty(key) && field_empty(key)){
                setIsValid(false)
                return
            }
        }
        setIsValid(true)

    }

    const renderReprezentacije = () => {
        return reprezentacije.map((reprezentacija)=><option key={reprezentacija.id} value={reprezentacija.id}>{reprezentacija.skraceniNaziv}</option>)
    }

    return (
        <>
            <Row>
                <Col></Col>
                <Col xs="12" sm="10" md="8">
                    <h1>Dodavanje Utakmica</h1>
                    <Form>
                        <Form.Group>
                            <Form.Label>Reprezentacija A</Form.Label>
                            <Form.Select id="reprezentacijaAId" name="reprezentacijaAId" onChange={(e) => reprezentacijaASelectionChanged(e)}> <br />
                            <option></option>
                            {renderReprezentacije()}
                            </Form.Select><br />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Reprezentacija B</Form.Label>
                            <Form.Select id="reprezentacijaBId" name="reprezentacijaBId" onChange={(e) => reprezentacijaBSelectionChanged(e)}> <br />
                            <option></option>
                            {renderReprezentacije()}
                            </Form.Select><br />
                        </Form.Group>
                        <Button  onClick={(event) => { event.preventDefault(); create(props, utakmica); }}>Add</Button>
                    </Form>
                </Col>
                <Col></Col>
            </Row>
        </>
    )
}





export default withNavigation(AddUtakmice);