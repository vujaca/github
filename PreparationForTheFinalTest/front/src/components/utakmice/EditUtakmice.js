import React, {useState, useCallback, useEffect } from 'react';
import Axios from '../../apis/Axios';
import {Col, Row, Form, Button} from 'react-bootstrap';
import { useNavigate, useParams } from 'react-router-dom';

const EditUtakmice = (props) => {
    const {reprezentacijaId} = useParams()
    console.log('Params', reprezentacijaId);
    console.log("Ponovno iscrtavanje")
    const [utakmica, setUtakmica] = useState({
        id: props.selectedUtakmica.id,
        reprezentacijaA: props.selectedUtakmica.reprezentacijaA,
        reprezentacijaB: props.selectedUtakmica.reprezentacijaB,
        brojGolovaA: props.selectedUtakmica.brojGolovaA,
        brojGolovaB: props.selectedUtakmica.brojGolovaB
    })
    const [reprezentacije, setReprezentacije] = useState([])
    const [izabranIgrac, setIzabranIgrac] = useState()
    const [igraci, setIgraci] = useState([])
    const [isValid, setIsValid] = useState(false)
    const navigate = useNavigate()

    useEffect(() => {
        getReprezentacije()
        getIgraci()
    }, [])


    const onInputChange = event => {
        const { name, value } = event.target;
        setUtakmica(utakmica => {
            var new_utakmica= {...utakmica}
            new_utakmica[name] = value
            console.log(new_utakmica)
            return new_utakmica
        });
    }

    const valueInputChanged = (e) => {
        let input = e.target;

        let name = input.name;
        let value = input.value;

        setIzabranIgrac(value);
        isInputValid()
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

    const field_empty = (key) => {
        const value = utakmica[key]
        return (value == null || value == "" || value == 0)
    }

    const getReprezentacije = useCallback(() => {
        Axios.get("/reprezentacije")
        .then(res => {
            console.log(res.data)
            setReprezentacije(res.data)
        })
        .catch(error => {
            console.log(error)
            alert('Greska prilikom preuzimanja reprezebtacuha')
        })
    }, [])

    const renderReprezentacije = () => {
        return reprezentacije.map((reprezentacija)=><option key={reprezentacija.id} value={reprezentacija.id}>{reprezentacija.naziv}</option>)
    }

    const getIgraci = useCallback(() => {
        Axios.get('/igraci'+'/reprezentacije/'+reprezentacijaId)
        .then(res => {
            console.log(res.data)
            setIgraci(res.data)
        })
        .catch(error => {
            console.log(error)
            alert('Greska prilikom preuzimanja igraca')
        })
    }, [])


    const renderIgraci = () => {
        console.log(igraci)
        return igraci.map((igrac)=><option key={igrac.id} value={igrac.id}>{igrac.ime + ' '+ igrac.prezime}</option>)
    }

    const postigniGol = () => {
        Axios.put('/igraci/strelac/'+izabranIgrac)
        .then(res => {
            console.log(res)
            alert('Strelac je ubelezen!')
            navigate('/utakmice')
        })
        .catch(err => {
            console.log(err)
            alert('Greska')
        })
    }

    return (
        <>
        <Row>
            <Col></Col>
            <Col xs="12" sm="10" md="8">
                <Form>
                    <Form.Group>
                        <Form.Label>Igraci</Form.Label>
                        <Form.Select name="igrac" onChange={(e) => valueInputChanged(e)} > <br />
                        <option></option>
                        {renderIgraci()}
                        </Form.Select><br />
                    </Form.Group>
                    <Button onClick={e => postigniGol()}>Postigni gol</Button>
                </Form>
            </Col>
            <Col></Col>
        </Row>
    </>
    )
}

export default EditUtakmice;