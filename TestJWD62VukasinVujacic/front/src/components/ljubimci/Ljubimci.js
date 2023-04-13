import React, { useEffect, useState } from 'react';
import Axios from '../../apis/Axios';
import { useNavigate } from 'react-router-dom';
import { Row, Col, Button, Table, Form } from 'react-bootstrap'
import LjubimacRow from './LjubimacRow';

const Ljubimci = (props) => {

    const empty_search = {
        kategorijaId: "",
        pol:"",
        opis: ""
    }
    const [search, setSearch] = useState(empty_search)
    const [totalPages, setTotalPages] = useState(0)
    const [ljubimci, setLjubimci] = useState([])
    const [pageNo, setPageNo] = useState(0)
    const [kategorije, setKategorije] = useState([])
    const navigate = useNavigate()


    useEffect(() => {
        getLjubimci(0)
        getKategorije()
    }, [])

    const getKategorije = () => {
        Axios.get("/kategorije")
        .then((resp) => {
            setKategorije(resp.data)
        })
        .catch((err=>{console.log(err)}))
    }

    const getLjubimci = (newPageNo) => {
        const conf = {
            params: {
                kategorijaId: search.kategorijaId,
                pol: search.pol,
        opis: search.opis,
                pageNo: newPageNo
            }
        }

        Axios.get("/ljubimci", conf)
            .then(res => {
                console.log(res)
                setLjubimci(res.data)
                setPageNo(newPageNo)
                setTotalPages(res.headers['total-pages'])
            })
            .catch(error => {
                console.log(error)
                alert('Greska prilikom ucitavnja ljubimaca')
            })
    }

    const goToAdd = () => {
        navigate('/ljubimci/add')
    }

    const renderSearchForm = () => {
        return (
            <>
            <Form style={{width: '99%'}}>
                <Row>
                    <Col>
                        <Form.Group>
                            <Form.Label>Kategorija</Form.Label>
                            <Form.Select
                                name = "kategorijaId" onChange={(e)=>onInputChange(e)}>
                                <option value=""></option>
                                {kategorije.map((kategorija)=>{
                                    return(
                                        <option key={kategorija.id} value={kategorija.id}>{kategorija.naziv}</option>
                                    );
                                })}
                                
                            </Form.Select>
                        </Form.Group>
                    </Col>

                </Row>
                <Row>
                    <Col>
                        <Form.Group>
                            <Form.Label>Pol</Form.Label>
                            <Form.Select
                                name = "pol" onChange={(e)=>onInputChange(e)}>
                               <option value=""></option>
                                <option value="muski">muski</option>
                                <option value="zenski">zenski</option>
                                
                            </Form.Select>
                        </Form.Group>
                    </Col>

                </Row>
                <Row>
                    <Col>
                        <Form.Group>
                            <Form.Label>Opis</Form.Label>
                            <Form.Control
                                name = "opis"
                                as = "input"
                                type = "text"
                                onChange={(e) => {onInputChange(e)}}>
                            </Form.Control>
                        </Form.Group>
                    </Col>
                </Row>
                <Row><Col>
                <Button className="btn btn-danger" onClick={() => getLjubimci(0)}>Search</Button><br/>
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

        const deleteLjubimca = (ljubimacId) => {
            Axios.delete('/ljubimci/' + ljubimacId)
                .then(res => {
                    // handle success
                    console.log(res)
                    alert('Ljubimac je uspesno obrisan!')
                    var nextPage
                if (pageNo === totalPages - 1 && ljubimci.length === 1) {
                    nextPage = pageNo - 1
                } else {
                    nextPage = pageNo
                }
                getLjubimci(nextPage)
                    // setLjubimci((ljubimci)=>ljubimci.filter(ljubimac => ljubimac.id != ljubimacId))
                })
                .catch(error => {
                    // handle error
                    console.log(error)
                    alert('Error occured please try again!')
                });
        }

        const promeniCheckbox = (ljubimacId) => {
            Axios.put('/ljubimci/promeni/'+ljubimacId) 
            .then(res => {
                console.log(res)
                getLjubimci(pageNo)
                alert('Uspesno')
            }
            )
            .catch(err => {
                console.log(err);
                alert('greska')
            })
            
        }


        const udomi = (ljubimacId) => {
            let udomljavanjeDTO = {
                ljubimacId:ljubimacId
            }
            Axios.post('/udomljavanja',udomljavanjeDTO)
            .then(res => {
                console.log(res)
                getLjubimci(pageNo)
                alert('Uspesno udomljavanje')
            })
            .catch(err => {
                console.log(err)
                alert('Nije uspelo udomljavanje, kucni ljubimac je mozda vec udomljen.')
            })
            }
    

        const renderLjubimca = () => {
            return ljubimci.map((ljubimac) => {
                return <LjubimacRow key={ljubimac.id} ljubimac={ljubimac}  deleteLjubimacCallback={deleteLjubimca} promenaCheckboxaCallback={promeniCheckbox} udomiCallback={udomi} ></LjubimacRow>
            })
        }


        const jwt = window.localStorage.getItem("jwt")

        //PAZNJA!!!!!!!!!!!!!!!!! U NAREDNE @ LINIJE KODA SE NALAZE ROLE
        // const admin = window.localStorage['role']=='ROLE_ADMIN'
        // const korisnik = window.localStorage['role']=='ROLE_KORISNIK'

    return (
        <div>
            <h1>Ljubimci</h1>
            {renderSearchForm()}
            <div>
                <Button onClick={() => goToAdd()}>Add</Button>
                <br />

                <Table id="movies-table">
                    <thead>
                        <tr>
                            <th>Ime</th>
                            <th>Starost</th>
                            {jwt?<th>Vakcinisan</th>:null}
                            <th>Pol</th>
                            <th>Tezina</th>
                            <th>Opis</th>
                            <th>Kategorija</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        {renderLjubimca()}
                    </tbody>
                </Table>
                <Button disabled={pageNo===0} 
                onClick={()=>getLjubimci(pageNo-1)}
                className='mr-3'>Prev</Button>
                <Button disabled={pageNo===totalPages-1 || totalPages===0}
                onClick={()=>getLjubimci(pageNo+1)}>Next</Button>
            </div>
        </div>
    )
}

export default Ljubimci;