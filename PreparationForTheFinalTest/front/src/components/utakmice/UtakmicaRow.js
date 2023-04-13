import { Button } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import Axios from "../../apis/Axios";


const UtakmicaRow = (props) => {

    var navigate = useNavigate()

        const goToDodajGol = (utakmica, index) => {
            let params = {
                ...utakmica,
                reprezentacijaAId:utakmica.reprezentacijaA.id,
                reprezentacijaBId:utakmica.reprezentacijaB.id
            }
            if (index == 1) {
                params = {...params,brojGolovaA: params.brojGolovaA+1}
            }else{
                params = {...params,brojGolovaB: params.brojGolovaB+1}
            }
            let reprezentacijaId = 0;
            if(index==1){
                reprezentacijaId=params.reprezentacijaAId
            }else {reprezentacijaId=params.reprezentacijaBId}
    
            Axios.put('/utakmice/'+utakmica.id, params)
            .then(res => {
                console.log(res)
                alert('Utakmica je uspesno izmenjena.')
                props.dodajGolCallback(utakmica);
            navigate('/utakmice/edit/'+reprezentacijaId);
            })
            .catch(err => {
                console.log(err)
                alert('Neuspesna izmena, pokusajte ponovo!')
            })
        }
    


    return (
        <tr>
           <td>{props.utakmica.reprezentacijaA.naziv}</td>
                <td>{props.utakmica.reprezentacijaB.naziv}</td>                       
                 <td>{props.utakmica.brojGolovaA}</td>
                <td>{props.utakmica.brojGolovaB}</td>
                <td><Button variant="warning" onClick={() => goToDodajGol(props.utakmica, 1)}>A+1</Button></td>
                <td><Button variant="warning" onClick={() => goToDodajGol(props.utakmica, 2)}>B+1</Button></td>
           <td><Button variant="danger" onClick={() => props.deleteUtakmicaCallback(props.utakmica.id)}>Delete</Button></td>
        </tr>
     )
}

export default UtakmicaRow;