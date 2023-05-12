import { Button} from "react-bootstrap";
import { useNavigate } from "react-router-dom";


const LinijaRow = (props) => {

    var navigate = useNavigate()

    const goToEdit = (linija) => {
        props.editCallback(linija);
        navigate('/linije/edit');
    }

    const jwt = window.localStorage.getItem("jwt")
    if(jwt){
            return (
                <tr>
                   <td>{props.linija.prevoznikNaziv}</td>
                        <td>{props.linija.destinacija}</td>                       
                         <td>{props.linija.brojMesta}</td>
                         <td>{props.linija.vremePolaska}</td>
                        <td>{props.linija.cenaKarte}</td>
                        
                        <td><Button disabled={props.linija.brojMesta==0} variant="danger" onClick={() => props.rezervisiCallback(props.linija.id)}>Rezervisi</Button></td>
                        <td><Button variant="warning" onClick={() => goToEdit(props.linija)}>Edit</Button></td>
                        {window.localStorage['role']=='ROLE_ADMIN'?<td><Button variant="danger" onClick={() => props.deleteLinijeCallback(props.linija.id)}>Delete</Button></td>:null}
                   {/* <td><Button variant="danger" onClick={() => props.deleteCallback(props.linija.id)}>Delete</Button></td> */}
                   
                </tr>
             )
        }
        
    
}

export default LinijaRow;