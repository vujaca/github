import React, { useEffect, useState } from 'react';
import CinemaAxios from '../../apis/CinemaAxios';
import { Row, Col, Button, Table, Form } from 'react-bootstrap'
import { useNavigate } from 'react-router-dom';
import MovieRow from './MovieRow';

const Movies = (props) => {

   const empty_search = {
        name: "",
        minDuration: "",
        maxDuration: "",
        id: ""
    }
    const [search, setSearch] = useState(empty_search)
    const [movies, setMovies] = useState([])
    const [genres, setGenres] = useState([])
    const [showSearch, setShowSearch] = useState(false)
    const navigate = useNavigate()

    useEffect(()=>{
        getMovies()
        getGenres()
    }, [])

    const getMovies = () => {
        let config = {
            params: {
                naziv: search.name,
                trajanjeOd: search.minDuration,
                trajanjeDo: search.maxDuration,
                zanrId: search.id,
            }
        }

        CinemaAxios.get('/filmovi', config)
            .then(res => {
                // handle success
                console.log(res)
                setMovies(res.data)
            })
            .catch(error => {
                // handle error
                console.log(error)
                alert('Error occured please try again!')
            });
    }

    const getGenres = () => {
        CinemaAxios.get("/zanrovi")
        .then((resp) => {
            setGenres(resp.data)
        })
        .catch((err=>{console.log(err)}))
    }

    const deleteMovie = (movieId) => {
        CinemaAxios.delete('/filmovi/' + movieId)
            .then(res => {
                // handle success
                console.log(res)
                alert('Movie was deleted successfully!')
                setMovies((movies)=>movies.filter(movie => movie.id != movieId))
            })
            .catch(error => {
                // handle error
                console.log(error)
                alert('Error occured please try again!')
            });
    }

    const onInputChange = (event) => {
        const name = event.target.name;
        const value = event.target.value

        search[name] = value;

        setSearch(search)
    }

    const goToAdd = () => {
        navigate('/movies/add');
    }

    const renderMovies = () => {
        return movies.map((movie) => {
            return <MovieRow key={movie.id} movie={movie} editCallback={props.callback} deleteMovieCallback={deleteMovie}></MovieRow>
        })
    }

    const renderSearchForm = () => {
        return (
            <>
            <Form style={{ width: "99%" }}>
                <Row><Col>
                    <Form.Group>
                        <Form.Label>Name</Form.Label>
                        <Form.Control
                            name="name"
                            as="input"
                            type="text"
                            onChange={(e) => onInputChange(e)}></Form.Control>
                    </Form.Group>
                </Col></Row>

                <Row>
                    <Col>
                        <Form.Group>
                            <Form.Label>Min Duration</Form.Label>
                            <Form.Control
                                name="minDuration"
                                as="input"
                                type="number"
                                onChange={(e) => onInputChange(e)}></Form.Control>
                        </Form.Group>
                    </Col>
                    <Col>
                        <Form.Group>
                            <Form.Label>Max Duration</Form.Label>
                            <Form.Control
                                name="maxDuration"
                                as="input"
                                type="number"
                                onChange={(e) => onInputChange(e)}></Form.Control>
                        </Form.Group>
                    </Col>
                </Row>
                <Row><Col>
                    <Form.Group>
                        <Form.Label>Genre</Form.Label>
                        <Form.Select name="id" onChange={(e)=>onInputChange(e)}>
                            <option value=""></option>
                            {genres.map((genre)=>{
                                return(
                                    <option value={genre.id}>{genre.naziv}</option>
                                );
                            })}
                        </Form.Select>
                    </Form.Group>
                </Col></Row>
            </Form>
            <Row><Col>
                <Button className="mt-3" onClick={() => getMovies()}>Search</Button>
            </Col></Row>
            </>
        );
    }
    
    return (
        <Col>
            <Row><h1>Movies</h1></Row>
            <div>
                <label>
                <input type="checkbox" onChange={()=>setShowSearch(!showSearch)}/>
                    Show Search
                </label>
            </div>
            <Row hidden={!showSearch} >
                {renderSearchForm()}
            </Row>
            <br/>
            
            {window.localStorage['role']=='ROLE_ADMIN'?
            <Row><Col>
            <Button onClick={() => goToAdd() }>Add</Button>
            </Col></Row>: null}

            <Row><Col>
            <Table style={{marginTop: 5}}>
            <thead>
            <tr>
            <th>Name</th>
            <th>Duration (min)</th>
            <th>Genres</th>
            <th></th>
            </tr>
            </thead>
            <tbody>
                {renderMovies()}
            </tbody>
            </Table>
            </Col></Row>
        </Col>
    );
}

export default Movies