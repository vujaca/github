import React, { useState, useEffect, useCallback } from 'react';
import { Row, Col, Form, Button } from "react-bootstrap";
import { useNavigate } from 'react-router-dom';
import Axios from '../../apis/Axios';
import { withNavigation } from '../../routeconf';

function AddLinije(props) {

    const empty_linija = {
        brojMesta: 0,
        cenaKarte: 0.00,
        destinacija: "",
        vremePolaska: "",
        prevoznikId: null
    }
    const [linija, setLinija] = useState(empty_linija)
    const [prevoznici, setPrevoznici] = useState([])
    const [isValid, setIsValid] = useState(false)
    const navigate = useNavigate()

    useEffect(() => {
        getPrevoznici();
    }, [])

    const getPrevoznici = useCallback(() => {
        Axios.get("/prevoznici")
        .then(res => {
            console.log(res.data)
            setPrevoznici(res.data)
        })
        .catch(error => {
            console.log(error)
            alert('Greska prilikom preuzimanja prevoznika')
        })
    }, [])


    const valueInputChanged = (e) => {
        let input = e.target;

        let name = input.name;
        let value = input.value;

        setLinija(linija => {
            linija[name] = value
            console.log(linija)
            return linija
        });
        isInputValid()
    }

    const prevoznikSelectionChanged = (e) => {
        let prevoznikId = e.target.value;
        //let prevoznik = prevoznici.find((prevoznik) => prevoznik.id == prevoznikId);

        setLinija(linija => {
            linija.prevoznikId = prevoznikId
            return linija
        });
        isInputValid()
    }

    const create = () => {
        let linijaDTO = {
            brojMesta: linija.brojMesta,
            cenaKarte: linija.cenaKarte,
            destinacija: linija.destinacija,
            vremePolaska: linija.vremePolaska,
            prevoznikId: linija.prevoznikId
        }

        Axios.post("/linije", linijaDTO)
        .then(() => {
            navigate('/linije');
        })
        .catch(error => {
            console.log(error)
            alert('Error while creating linija')
        })
    }

    const field_empty = (key) => {
        const value = linija[key]
        return (value == null || value == "" || value == 0)
    }

    const isInputValid = () => {
        //proveravamo da li je bilo koje polje ostalo prazno
        for (const key in linija) {
            if (linija.hasOwnProperty(key) && field_empty(key)){
                setIsValid(false)
                return
            }
            const timeRegex = new RegExp( "^([01][0-9]|2[0-3]):[0-5][0-9]$");
        if(!timeRegex.test(linija.vremePolaska)){
                setIsValid(false)
                return
            }
        }
        setIsValid(true)

    }

    // const renderVinarije = () => {
    //     return vinarije.map((vinarija)=><option key={vinarija.id} value={vinarija.id}>{vinarija.ime}</option>)
    // }

    const renderPrevoznici = () => {
        return prevoznici.map((prevoznik)=><option key={prevoznik.id} value={prevoznik.id}>{prevoznik.naziv}</option>)
    }

    return (
        <>
            <Row>
                <Col></Col>
                <Col xs="12" sm="10" md="8">
                    <h1>Add Linija</h1>
                    <Form>
                        <Form.Group>
                            <Form.Label>Broj mesta</Form.Label>
                            <Form.Control
                             step="1"  type="number" id="brojMesta" name="brojMesta" onChange={(e) => valueInputChanged(e)} /> <br />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Cena karte</Form.Label>
                            <Form.Control
                               step=".1" type="number" id="cenaKarte" name="cenaKarte" onChange={(e) => valueInputChanged(e)} /> <br />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Destinacija</Form.Label>
                            <Form.Control id="destinacija" name="destinacija" onChange={(e) => valueInputChanged(e)} /> <br />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Vreme polaska</Form.Label>
                            <Form.Control  id="vremePolaska" name="vremePolaska" onChange={(e) => valueInputChanged(e)} /> <br />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Prevoznik</Form.Label>
                            <Form.Select id="prevoznikId" name="prevoznikId" onChange={(e) => prevoznikSelectionChanged(e)}> <br />
                            <option></option>
                            {renderPrevoznici()}
                            </Form.Select><br />
                        </Form.Group>
                        <Button  onClick={(event) => { event.preventDefault(); create(props, linija); }}>Add</Button>
                    </Form>
                </Col>
                <Col></Col>
            </Row>
        </>
    )
}





export default withNavigation(AddLinije);