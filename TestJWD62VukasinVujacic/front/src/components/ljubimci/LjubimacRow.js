import { Button} from "react-bootstrap";


const LjubimacRow = (props) => {


    const isVakcinisan = props.ljubimac.vakcinisan===true
    const admin = window.localStorage['role']==='ROLE_ADMIN'
        const korisnik = window.localStorage['role']==='ROLE_KORISNIK'
        const vakcinisanAdmin = admin&&isVakcinisan
        const nevakcinisanAdmin = admin && !isVakcinisan
        const vakcinisanKorisnik = korisnik && isVakcinisan
        const nevakcinisanKorisnik = korisnik && !isVakcinisan

            return (
                <tr key={props.ljubimac.id}>
                   <td>{props.ljubimac.ime}</td>
                        <td>{props.ljubimac.starost}</td>                       
                            {vakcinisanAdmin===true?<td><input type="checkbox" checked onChange={e => props.promenaCheckboxaCallback(props.ljubimac.id)}></input></td>:null}
                            {nevakcinisanAdmin===true?<td><input type="checkbox" onChange={e => props.promenaCheckboxaCallback(props.ljubimac.id)} ></input></td>:null}
                            {vakcinisanKorisnik===true?<td>Vakcinisan</td>:null}
                            {nevakcinisanKorisnik===true?<td>Nije vakcinisan</td>:null}
                            <td>{props.ljubimac.pol}</td>
                        <td>{props.ljubimac.tezina}</td>
                        <td>{props.ljubimac.opis}</td>
                        <td>{props.ljubimac.kategorijaNaziv}</td>
                    
                        {admin?<td><Button variant="danger" onClick={() => props.deleteLjubimacCallback(props.ljubimac.id)}>Obrisi</Button></td>:<td></td>}
                        {korisnik&&props.ljubimac.vakcinisan===true?<td><Button variant="warning" onClick={() => props.udomiCallback(props.ljubimac.id)}>Udomi</Button></td>:<td></td>}
                   
                </tr>
             )
       // }
        
    
}

export default LjubimacRow;