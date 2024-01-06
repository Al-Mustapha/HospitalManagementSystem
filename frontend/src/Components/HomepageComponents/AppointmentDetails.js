import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import {viewAppointmentDetails, initiatePayment} from "../MyClient";
import {Button, Table} from "antd";

const AppointmentDetails = () => {

    const {appointmentId} = useParams();

    const [details, setDetails] = useState({});

    const [appId, setAppointmentId] = useState(appointmentId);
    const [appointmentDate, setAppointmentDate] = useState("");
    const [appointmentTime, setAppointmentTime] = useState("");
    const [appointmentStatus, setAppointmentStatus] = useState("");
    const [appointmentPaymentStatus, setAppointmentPaymentStatus] = useState("");
    const [patient, setPatient] = useState(null);


    const [appointmentDetails, setAppointmentDetails] = useState({
        appointmentId: appointmentId,
        appointmentDate: "",
        appointmentTime: "",
        appointmentStatus: "",
        paymentStatus: "",
        patientId: null
    });

    const fetchAppointmentDetails = () => {
        viewAppointmentDetails(appointmentId)
            .then(response => response.json())
            .then((data) =>
                {
                setAppointmentId(data.appointmentId)
                    setAppointmentDate(data.appointmentDate)
                    setAppointmentTime(data.appointmentTime)
                    setAppointmentStatus(data.appointmentStatus)
                    setAppointmentPaymentStatus(data.paymentStatus)
                    setPatient(data.patientId)
                }
            )
            .then(error => console.log(error))
    }

    useEffect(()=> {
        fetchAppointmentDetails()
    },[])

    const appointmentProperties = appointmentDetails;


    const pay = () => {
        initiatePayment(appId)
            .then(response => console.log(response))
    }

    return(
        <div style={{marginTop:'50px'}}>
            <center><h1>APPOINTMENT DETAILS</h1></center>
            <table border="1" valign="left" align="center" style={{textAlign:'left', borderColor:'black'}}>
                <tbody>
                    <tr>
                        <td>ID</td>
                        <td>{appId}</td>
                    </tr>

                    <tr>
                        <td>USER</td>
                        <td>{}</td>
                    </tr>

                    <tr>
                        <td>DATE</td>
                        <td>{appointmentDate}</td>
                    </tr>

                    <tr>
                        <td>TIME</td>
                        <td>{appointmentTime}</td>
                    </tr>

                    <tr>
                        <td>STATUS</td>
                        <td>{appointmentStatus}</td>
                    </tr>

                    <tr>
                        <td>PAYMENT STATUS</td>
                        <td>{appointmentPaymentStatus}</td>
                    </tr>
                </tbody>
            </table>
            <br></br>
            <Button style={{width:'100px', backgroundColor:'green',color:'white'}}
            onClick={pay}
            >PAY NOW</Button>  <Button style={{width:'100px', backgroundColor:'red',color:'white'}}>EDIT</Button>
        </div>
    )
}
export default AppointmentDetails;