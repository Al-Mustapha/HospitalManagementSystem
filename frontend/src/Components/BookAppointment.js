import {DatePicker, TimePicker, Button} from "antd";
import {useState} from "react";
import {createAppointment} from "./MyClient";
import {useNavigate} from "react-router-dom";

const selectorsStyle = {
    width:'200px',
    height:'50px'
}

const BookAppointment = () => {

    const navigate = useNavigate();

    const [appDate, setAppDate] = useState(null);
    const [appTime, setAppTime] = useState(null);

    const [appointmentDetails, setAppointmentDetails] = useState({
        appointmentId:"",
        appointmentTime:"",
        appointmentDate:""
    });

    const [appointmentInitiatedSuccessfully, setAppointmentInitiatedSuccessfully] = useState(false)

    let details = null;

    const bookAppointment = () => {
        createAppointment(appDate.toString(), appTime.toString())
            .then((response) => {
                        console.log(response)
                setAppointmentDetails(response.data)
                if (response.status==200)
                    setAppointmentInitiatedSuccessfully(true)
                        // localStorage.setItem("appointmentDetails", response.data)
                }
            )
            .then(error => console.log(error))
    }

    if (appointmentInitiatedSuccessfully){
        navigate("/appointmentDetails/" + appointmentDetails.appointmentId)
    }

    return(
        <>
            <div style={{padding:'100px'}}>
        <label htmlFor="appDate">Appointment Date</label> <DatePicker name="appDate"
                                 style={selectorsStyle}
            value={appDate}
            onChange={(date) => setAppDate(date)}
            ></DatePicker>
        <br></br>
        <br></br>
        <label htmlFor="appointmentTime">Appointment Time</label> <TimePicker name="appointmentTime"
           style={selectorsStyle}
           value={appTime}
           onChange={(time) => setAppTime(time)}
            ></TimePicker>
        <br></br>
                <br></br>
        <Button style={{backgroundColor:'green', color:'white'}}
        onClick={bookAppointment}
        >Book Appointment</Button>
            </div>
        </>
    )
}
export default BookAppointment;