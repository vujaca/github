import React, { useEffect, useState } from 'react';
import Axios from '../../apis/Axios';
import { useNavigate } from 'react-router-dom';
import { Row, Col, Button, Table, Form } from 'react-bootstrap'
import LinijaRow from './LinijaRow';

const Linije = (props) => {

    const empty_search = {
        destinacija: "",
        cenaKarteDo: "",
        prevoznikId: ""
    }
    const [search, setSearch] = useState(empty_search)
    const [totalPages, setTotalPages] = useState(0)
    const [linije, setLinije] = useState([])
    const [pageNo, setPageNo] = useState(0)
    const [prevoznici, setPrevoznici] = useState([])
    const navigate = useNavigate()
    const [isValid, setIsValid] = useState(false)
    //DODAVANJE NA ISTOJ STRANICI  const empty_linija = {
    //     brojMesta: 0,
    //     cenaKarte: 0.00,
    //     destinacija: "",
    //     vremePolaska: "",
    //     prevoznikId: null
    // }
    // const [linijaAdd, setLinijaAdd] = useState(empty_linija)

    useEffect(() => {
        getLinije(0)
        getPrevoznici()
    }, [])

    const getPrevoznici = () => {
        Axios.get("/prevoznici")
        .then((resp) => {
            setPrevoznici(resp.data)
        })
        .catch((err=>{console.log(err)}))
    }

    const getLinije = (newPageNo) => {
        const conf = {
            params: {
                destinacija: search.destinacija,
                cenaKarteDo: search.cenaKarteDo,
                prevoznikId: search.prevoznikId,
                pageNo: newPageNo
            }
        }

        Axios.get("/linije", conf)
            .then(res => {
                console.log(res)
                setLinije(res.data)
                setPageNo(newPageNo)
                setTotalPages(res.headers['total-pages'])
            })
            .catch(error => {
                console.log(error)
                alert('Error while fetching lines')
            })
    }

    //DODAVANJE NA ISTOJ STRANICI  const field_empty = (key) => {
    //     const value = linijaAdd[key]
    //     return (value == null || value == "" || value == 0)
    // }

    // const isInputValid = () => {
    //     //proveravamo da li je bilo koje polje ostalo prazno
    //     for (const key in linijaAdd) {
    //         if (linijaAdd.hasOwnProperty(key) && field_empty(key)){
    //             setIsValid(false)
    //             return
    //         }
    //         const timeRegex = new RegExp( "^([01][0-9]|2[0-3]):[0-5][0-9]$");
    //     if(!timeRegex.test(linijaAdd.vremePolaska)){
    //             setIsValid(false)
    //             return
    //         }
    //     }
    //     setIsValid(true)

    // }

    // const create = () => {
    //     let linijaDTO = {
    //         brojMesta: linijaAdd.brojMesta,
    //         cenaKarte: linijaAdd.cenaKarte,
    //         destinacija: linijaAdd.destinacija,
    //         vremePolaska: linijaAdd.vremePolaska,
    //         prevoznikId: linijaAdd.prevoznikId
    //     }

    //     Axios.post("/linije", linijaDTO)
    //     .then(() => {
    //         getLinije(pageNo)
    //         navigate('/linije');
    //     })
    //     .catch(error => {
    //         console.log(error)
    //         alert('Error while creating linija')
    //     })
    // }

    const goToAdd = () => {
        navigate('/linije/add')
    }

    const renderSearchForm = () => {
        return (
            <>
            <Form style={{width: '99%'}}>
            <Row>
                    <Col>
                        <Form.Group>
                            <Form.Label>Destinacija</Form.Label>
                            <Form.Control
                                name = "destinacija"
                                as = "input"
                                type = "text"
                                onChange={(e) => {onInputChange(e)}}>
                            </Form.Control>
                        </Form.Group>
                    </Col>
                </Row>
                <Row>
                    <Col>
                        <Form.Group>
                            <Form.Label>Cena max</Form.Label>
                            <Form.Control
                                name = "cenaKarteDo"
                                as = "input"
                                type = "number"
                                step= "0.1"
                                onChange={(e) => {onInputChange(e)}}>
                            </Form.Control>
                        </Form.Group>
                    </Col>
                </Row>
                <Row>
                    <Col>
                        <Form.Group>
                            <Form.Label>Prevoznik</Form.Label>
                            <Form.Select
                                name = "prevoznikId" onChange={(e)=>onInputChange(e)}>
                                <option value=""></option>
                                {prevoznici.map((prevoznik)=>{
                                    return(
                                        <option key={prevoznik.id} value={prevoznik.id}>{prevoznik.naziv}</option>
                                    );
                                })}
                                
                            </Form.Select>
                        </Form.Group>
                    </Col>

                </Row>
                <Row><Col>
                <Button className="btn btn-danger" onClick={() => getLinije(0)}>Search</Button><br/>
            </Col></Row>
            </Form>
            
            
            </>
        )

        }

        //DODAVANJE NA ISTOJ STRANICI const valueInputChanged = (e) => {
        //     let input = e.target;
    
        //     let name = input.name;
        //     let value = input.value;
    
        //     setLinijaAdd(linijaAdd => {
        //         linijaAdd[name] = value
        //         console.log(linijaAdd)
        //         return linijaAdd
        //     });
        //     isInputValid()
        // }

        const onInputChange = (event) => {
            const name = event.target.name;
            const value = event.target.value
    
            search[name] = value;
    
            setSearch(search)
        }

        const deleteLinije = (linijaId) => {
            Axios.delete('/linije/' + linijaId)
                .then(res => {
                    // handle success
                    console.log(res)
                    alert('Linija je uspesno obrisana!')
                    setLinije((linije)=>linije.filter(linija => linija.id != linijaId))
                })
                .catch(error => {
                    // handle error
                    console.log(error)
                    alert('Error occured please try again!')
                });
        }

        const rezervisi = (linijaId) => {
            Axios.put('/linije/rezervisi/'+linijaId)
            .then(res => {
                console.log(res)
                getLinije(pageNo)
                alert('Uspesna rezervacija')
            })
            .catch(err => {
                console.log(err)
                alert('Error occured please try again!')
            })
        }

        // const goToEdit = (linija) => {
        //     navigate('/linije/edit/'+ linija)
        // }

        const renderLinije = () => {
            return linije.map((linija) => {
                return <LinijaRow key={linija.id} linija={linija} rezervisiCallback={rezervisi} deleteLinijeCallback={deleteLinije} editCallback={props.callback}></LinijaRow>
            })
        }

        const renderPrevoznici = () => {
            return prevoznici.map((prevoznik)=><option key={prevoznik.id} value={prevoznik.id}>{prevoznik.naziv}</option>)
        }

        //PAZNJA!!!!!!!!!!!!!!!!! U NAREDNE @ LINIJE KODA SE NALAZE ROLE
        // const admin = window.localStorage['role']=='ROLE_ADMIN'
        // const korisnik = window.localStorage['role']=='ROLE_KORISNIK'

    return (
        <div>
            <h1>Linije</h1>
            {/*DODAVANJE NA ISTOJ STRANICI <Row>
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
                            <Form.Select id="prevoznikId" name="prevoznikId" onChange={(e) => valueInputChanged(e)}> <br />
                            <option></option>
                            {renderPrevoznici()}
                            </Form.Select><br />
                        </Form.Group>
                        <Button  onClick={(event) => { event.preventDefault(); create(props, linijaAdd); }}>Add</Button>
                    </Form>
                </Col>
                <Col></Col>
            </Row> */}
            {renderSearchForm()}
            <div>
                <Button onClick={() => goToAdd()}>Add</Button>
                <br />

                <Table id="movies-table">
                    <thead>
                        <tr>
                            <th>Naziv prevoznika</th>
                            <th>Destinacija</th>
                            <th>Broj mesta</th>
                            <th>Vreme polaska</th>
                            <th>Cena karte</th>
                            <th></th>
                            <th></th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        {renderLinije()}
                    </tbody>
                </Table>
                <Button disabled={pageNo===0} 
                onClick={()=>getLinije(pageNo-1)}
                className='mr-3'>Prev</Button>
                <Button disabled={pageNo==totalPages-1 || totalPages==0}
                onClick={()=>getLinije(pageNo+1)}>Next</Button>
            </div>
        </div>
    )
}

export default Linije;