import React,{useState} from "react";
import { createRoot } from "react-dom/client";
import { Route, Link, BrowserRouter as Router, Routes } from "react-router-dom";
import { Container, Nav, Navbar, Button } from "react-bootstrap";
import Home from "./components/Home";
import NotFound from "./components/NotFound";
import {logout} from './services/auth';
import Login from './components/authorization/Login';
import Ljubimci from './components/ljubimci/Ljubimci'
import AddLjubimca from "./components/ljubimci/AddLjubimca";
//import Edit from "./components/linije/EditLinije";


const App = () => {

    const [selectedLjubimac, setSelectedLjubimac] = useState({})

    const jwt = window.localStorage.getItem("jwt")
    if (jwt){    return(
        <>
            <Router>
                <Navbar expand bg="dark" variant="dark">
                    <Navbar.Brand as={Link} to="/">
                        JWD
                    </Navbar.Brand>
                    <Nav>
                        <Nav.Link as={Link} to="/ljubimci">
                            Ljubimci
                        </Nav.Link>
                        <Button onClick={() => logout()}>Log out</Button>
                    </Nav>
                </Navbar>
                <Container style={{paddingTop:"10px"}}>
                    <Routes>
                        <Route path="/" element={<Home/>}/>
                        <Route path="/ljubimci" element={<Ljubimci callback={(ljubimac) =>setSelectedLjubimac(ljubimac)}/>}/>
                        <Route path="/ljubimci/add" element={<AddLjubimca/>}/>
                        {/* <Route path="/linije/edit" element={<EditLinije selectedLinija={selectedLinija}/>}/> */}
                        <Route path="/login" element={<Login/>}/>
                        <Route path="*" element={<NotFound/>}/>
                    </Routes>
                </Container>
            </Router>
        
        </>
    )}
    else{
        return(
            <>
            <Router>
                <Navbar expand bg="dark" variant="dark">
                    <Navbar.Brand as={Link} to="/">
                        JWD
                    </Navbar.Brand>
                    <Nav>
                        <Nav.Link as={Link} to="/ljubimci">
                            Ljubimci
                        </Nav.Link>
                        <Nav.Link as={Link} to="/login">
                            Login
                        </Nav.Link>
                    </Nav>
                </Navbar>
                <Container style={{paddingTop:"10px"}}>
                    <Routes>
                        <Route path="/" element={<Home/>}/>
                        <Route path="/ljubimci" element={<Ljubimci callback={(ljubimac) =>setSelectedLjubimac(ljubimac)}/>}/>
                        <Route path="/login" element={<Login/>}/>
                        <Route path="*" element={<NotFound/>}/>
                    </Routes>
                </Container>
            </Router>
        
        </>
        )
    }



}

const rootElement = document.getElementById('root')
const root = createRoot(rootElement)

root.render(
    <App/>,
);