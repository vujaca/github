import React, { useState, useEffect, useCallback } from 'react';
import { Row, Col, Form, Button } from "react-bootstrap";
import { useNavigate } from 'react-router-dom';
import Axios from '../../apis/Axios';
import { withNavigation } from '../../routeconf';

function AddLjubimca(props) {

    const empty_ljubimac = {
        ime: "",
        starost: 0,
        pol: "",
        tezina: 0.0,
        opis: "",
        kategorijaId: null
    }
    const [ljubimac, setLjubimac] = useState(empty_ljubimac)
    const [kategorije, setKategorije] = useState([])
    const [isValid, setIsValid] = useState(false)
    const navigate = useNavigate()

    useEffect(() => {
        getKategorije();
    }, [])

    const getKategorije = useCallback(() => {
        Axios.get("/kategorije")
        .then(res => {
            console.log(res.data)
            setKategorije(res.data)
        })
        .catch(error => {
            console.log(error)
            alert('Greska prilikom preuzimanja kategorija')
        })
    }, [])


    const valueInputChanged = (e) => {
        let input = e.target;

        let name = input.name;
        let value = input.value;

        setLjubimac(ljubimac => {
            ljubimac[name] = value
            console.log(ljubimac)
            return ljubimac
        });
        isInputValid()
    }

    const kategorijaSelectionChanged = (e) => {
        let kategorijaId = e.target.value;
        //let prevoznik = prevoznici.find((prevoznik) => prevoznik.id == prevoznikId);

        setLjubimac(ljubimac => {
            ljubimac.kategorijaId = kategorijaId
            return ljubimac
        });
        isInputValid()
    }

    const create = () => {
        let ljubimacDTO = {
            ime: ljubimac.ime,
        starost: ljubimac.starost,
        pol: ljubimac.pol,
        tezina: ljubimac.tezina,
        opis: ljubimac.opis,
        kategorijaId: ljubimac.kategorijaId
        }

        Axios.post("/ljubimci", ljubimacDTO)
        .then(() => {
            navigate('/ljubimci');
        })
        .catch(error => {
            console.log(error)
            alert('Error while creating pet')
        })
    }

    const field_empty = (key) => {
        const value = ljubimac[key]
        return (value === null || value === "" || value === 0)
    }

    const isInputValid = () => {
        //proveravamo da li je bilo koje polje ostalo prazno
        for (const key in ljubimac) {
            if (ljubimac.hasOwnProperty(key) && field_empty(key)){
                setIsValid(false)
                return
            }}
        setIsValid(true)

    }

    const renderKategorije = () => {
        return kategorije.map((kategorija)=><option key={kategorija.id} value={kategorija.id}>{kategorija.naziv}</option>)
    }

    return (
        <>
            <Row>
                <Col></Col>
                <Col xs="12" sm="10" md="8">
                    <h1>
                    Dodavanje Ljubimca
                    </h1>
                    <Form>
                        <Form.Group>
                            <Form.Label>Ime</Form.Label>
                            <Form.Control
                             id="ime" name="ime" onChange={(e) => valueInputChanged(e)} /> <br />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Starost</Form.Label>
                            <Form.Control
                               step="1" type="number" id="starost" name="starost" onChange={(e) => valueInputChanged(e)} /> <br />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Pol</Form.Label>
                            <Form.Select name = "pol" id="pol" onChange={(e)=>valueInputChanged(e)}> <br />
                                <option value=""></option>
                                <option value="muski">muski</option>
                                <option value="zenski">zenski</option>
                            </Form.Select><br />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Tezina</Form.Label>
                            <Form.Control type="number" step="0.1" id="tezina" name="tezina" onChange={(e) => valueInputChanged(e)} /> <br />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Opis</Form.Label>
                            <Form.Control id="opis" name="opis" onChange={(e) => valueInputChanged(e)} /> <br />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Kategorija</Form.Label>
                            <Form.Select id="kategorijaId" name="kategorijaId" onChange={(e) => kategorijaSelectionChanged(e)}> <br />
                            <option></option>
                            {renderKategorije()}
                            </Form.Select><br />
                        </Form.Group>
                        <Button disabled={!isValid} onClick={(event) => { event.preventDefault(); create(props, ljubimac); }}>Add</Button>
                    </Form>
                </Col>
                <Col></Col>
            </Row>
        </>
    )
}





export default withNavigation(AddLjubimca);