import React, { useEffect, useState } from 'react';
import Axios from '../../apis/Axios';
import { useNavigate } from 'react-router-dom';
import { Row, Col, Button, ButtonGroup, Table, Form } from 'react-bootstrap'
import UtakmicaRow from './UtakmicaRow';

const Utakmice = (props) => {

    const empty_search = {
        reprezentacijaAId: "",
        reprezentacijaBId: ""
    }
    const [search, setSearch] = useState(empty_search)
    const [totalPages, setTotalPages] = useState(0)
    const [utakmice, setUtakmice] = useState([])
    const [pageNo, setPageNo] = useState(0)
    const [reprezentacije, setReprezentacije] = useState([])
    const [igraci, setIgraci] = useState([])
    const navigate = useNavigate()

    useEffect(() => {
        getUtakmice(0)
        getReprezentacije()
        getIgraci()
    }, [])

    const getReprezentacije = () => {
        Axios.get("/reprezentacije")
        .then((resp) => {
            setReprezentacije(resp.data)
        })
        .catch((err=>{console.log(err)}))
    }

    const getIgraci = () => {
        Axios.get("/igraci")
        .then((resp) => {
            setIgraci(resp.data)
        })
        .catch((err=>{console.log(err)}))
    }

    const getUtakmice = (newPageNo) => {
        const conf = {
            params: {
                reprezentacijaAId: search.reprezentacijaAId,
        reprezentacijaBId: search.reprezentacijaBId,
                pageNo: newPageNo
            }
        }

        Axios.get("/utakmice", conf)
            .then(res => {
                console.log(res)
                setUtakmice(res.data)
                setPageNo(newPageNo)
                setTotalPages(res.headers['total-pages'])
            })
            .catch(error => {
                console.log(error)
                alert('Error while fetching matches')
            })
    }

    const goToAdd = () => {
        navigate('/utakmice/add')
    }

    const renderSearchForm = () => {
        return (
            <>
            <Form style={{width: '99%'}}>
                <Row>
                    <Col>
                        <Form.Group>
                        <Form.Label>Reprezentacija A</Form.Label>
                            <Form.Select name="reprezentacijaAId" onChange={(e)=>onInputChange(e)}>
                            <option value=""></option>
                            {reprezentacije.map((reprezentacija)=>{
                                return(
                                    <option key={reprezentacija.id} value={reprezentacija.id}>{reprezentacija.skraceniNaziv}</option>
                                );
                            })}
                        </Form.Select>
                        </Form.Group>
                    </Col>

                </Row>
                <Row>
                    <Col>
                        <Form.Group>
                            <Form.Label>Reprezentacija B</Form.Label>
                            <Form.Select name="reprezentacijaBId" onChange={(e)=>onInputChange(e)}>
                            <option value=""></option>
                            {reprezentacije.map((reprezentacija)=>{
                                return(
                                    <option key={reprezentacija.id} value={reprezentacija.id}>{reprezentacija.skraceniNaziv}</option>
                                );
                            })}
                        </Form.Select>
                        </Form.Group>
                    </Col>

                </Row>
                <Row><Col>
                <Button className="btn btn-danger" onClick={() => getUtakmice(0)}>Search</Button><br/>
            </Col></Row>
            </Form>
            
            
            </>
        )

        }

        const onInputChange = (event) => {
            const name = event.target.name;
            const value = event.target.value
    
            search[name] = value;
    
            setSearch(search)
        }

        const deleteUtakmica = (utakmicaId) => {
            Axios.delete('/utakmice/' + utakmicaId)
                .then(res => {
                    // handle success
                    console.log(res)
                    alert('Utakmica je uspesno obrisana!')
                    setUtakmice((utakmice)=>utakmice.filter(utakmica => utakmica.id != utakmicaId))
                })
                .catch(error => {
                    // handle error
                    console.log(error)
                    alert('Error occured please try again!')
                });
        }

        // const goToEdit = (linija) => {
        //     navigate('/linije/edit/'+ linija)
        // }

        const renderUtakmice = () => {
            console.log(utakmice);
            if (utakmice.length == 0) return null;
            return utakmice.map((utakmica) => {
                return <UtakmicaRow key={utakmica.id} utakmica={utakmica} dodajGolCallback={props.callback} deleteUtakmicaCallback={deleteUtakmica}></UtakmicaRow>
            })
        }

        const najboljiStrelac = () => {
            let najboljiStrelac = igraci[1]
            for(let strelac in igraci) {
                console.log(strelac)
                if(igraci[strelac].postignutiGolovi > najboljiStrelac.postignutiGolovi){
                    najboljiStrelac = igraci[strelac]
                }
            }
            alert('Najbolji strelac je '+najboljiStrelac.ime+' '+najboljiStrelac.prezime)
            return najboljiStrelac;
        }

    return (
        <div>
            <h1>Utakmice</h1>
            {renderSearchForm()}
            <br />
            <div>
            <div>
                        <Button style={{ margin: 3, width: 150}}  className='col-12' onClick={() => goToAdd()}>Dodaj Utakmicu</Button>
                    </div>
                <Table  bordered striped style={{ marginTop: -3 }} id="movies-table">
                    <thead  style={{ backgroundColor: 'green', color: 'white' }} >
                        <tr>
                            <th>Reprezentacija A</th>
                            <th>Reprezentacija B</th>
                            <th>Golovi A</th>
                            <th>Golovi B</th>
                            <th></th>
                            <th></th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        {renderUtakmice()}
                    </tbody>
                </Table>
                <ButtonGroup style={{ marginTop: -19, float: "right" }}>
                    <Button style={{ margin: 3, width: 90 }} disabled={pageNo === 0}
                        onClick={() => getUtakmice(pageNo - 1)}
                        className='mr-3'>Prethodna</Button>
                    <Button style={{ margin: 3, width: 90 }} disabled={pageNo == totalPages - 1 || totalPages == 0}
                        onClick={() => getUtakmice(pageNo + 1)}
                        className='mr+3'
                    >Sledeca</Button>
                    </ButtonGroup>
                <br />
            </div>
            < br/>
            <div className='text-center'>
            <Button variant='success' onClick={e => najboljiStrelac()}>Najbolji strelac</Button>
            </div>
        </div>
    )
}

export default Utakmice;