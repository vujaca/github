import React, {useState, useCallback, useEffect } from 'react';
import Axios from '../../apis/Axios';
import {Col, Row, Form, Button} from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';

const EditLinije = (props) => {

    console.log("Ponovno iscrtavanje")
    const [linija, setLinija] = useState({
        id: props.selectedLinija.id,
        brojMesta: props.selectedLinija.brojMesta,
        cenaKarte: props.selectedLinija.cenaKarte,
        destinacija: props.selectedLinija.destinacija,
        vremePolaska: props.selectedLinija.vremePolaska,
        prevoznikId: props.selectedLinija.prevoznikId
    })
    const [prevoznici, setPrevoznici] = useState([])
    const [isValid, setIsValid] = useState(false)
    const navigate = useNavigate()

    useEffect(() => {
        getPrevoznici();
    }, [])

    const onInputChange = event => {
        const { name, value } = event.target;
        setLinija(linija => {
            var new_linija= {...linija}
            new_linija[name] = value
            console.log(new_linija)
            return new_linija
        });
    }

    // const prevoznikSelectionChanged = e => {
    //     let prevoznikId = e.target.value;
    //     //let prevoznik = prevoznici.find((prevoznik) => prevoznik.id == prevoznikId);

    //     setLinija(linija => {
    //         var new_linija=linija
    //         new_linija.prevoznikId = prevoznikId
    //         return new_linija
    //     });
    // }

    const isInputValid = () => {
        //proveravamo da li je bilo koje polje ostalo prazno
        for (const key in linija) {
            if (linija.hasOwnProperty(key) && field_empty(key)){
                setIsValid(false)
                return
            }
        }
        const timeRegex = new RegExp( "^([01][0-9]|2[0-3]):[0-5][0-9]$");
        if(!timeRegex.test(linija.vremePolaska)){
                setIsValid(false)
                return
            }
            setIsValid(true)
        }
        

    

    const field_empty = (key) => {
        const value = linija[key]
        return (value == null || value == "" || value == 0)
    }

    const edit = () => {
        const params = {
            id: linija.id,
            brojMesta: linija.brojMesta,
            cenaKarte: linija.cenaKarte,
            destinacija: linija.destinacija,
            vremePolaska: linija.vremePolaska,
            prevoznikId: linija.prevoznikId
        }

        Axios.put('/linije/'+linija.id, params)
        .then(res => {
            console.log(res)
            alert('Linija je uspesno izmenjena.')
            navigate('/linije')
        })
        .catch(err => {
            console.log(err)
            alert('Neuspesna izmena, pokusajte ponovo!')
        })
    }

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

    const renderPrevoznici = () => {
        return prevoznici.map((prevoznik)=><option key={prevoznik.id} value={prevoznik.id}>{prevoznik.naziv}</option>)
    }

    return (
        <>
        <Row>
            <Col></Col>
            <Col xs="12" sm="10" md="8">
                <h1>Edit Linija</h1>
                <Form>
                        <Form.Group>
                            <Form.Label>Broj mesta</Form.Label>
                            <Form.Control
                             step="1"  type="number" id="brojMesta" name="brojMesta" value={linija.brojMesta} onChange={(e) => onInputChange(e)} /> <br />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Cena karte</Form.Label>
                            <Form.Control
                               step=".1" type="number" id="cenaKarte" name="cenaKarte" value={linija.cenaKarte} onChange={(e) => onInputChange(e)} /> <br />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Destinacija</Form.Label>
                            <Form.Control id="destinacija" name="destinacija"  value={linija.destinacija} onChange={(e) => onInputChange(e)} /> <br />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Vreme polaska</Form.Label>
                            <Form.Control  id="vremePolaska" name="vremePolaska" value={linija.vremePolaska} onChange={(e) => onInputChange(e)} /> <br />
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Prevoznik</Form.Label>
                            <Form.Select id="prevoznikId" name="prevoznikId" value={linija.prevoznikId} onChange={(e) => onInputChange(e)}> <br />
                            <option></option>
                            {renderPrevoznici()}
                            </Form.Select><br />
                        </Form.Group>
                    <Button onClick={() => edit()}>Edit</Button>
                </Form>
            </Col>
            <Col></Col>
        </Row>
    </>
    )

    }

export default EditLinije;