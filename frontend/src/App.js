//import 'crypto-browserify'
import './App.css';

import {BrowserRouter, Route, Routes} from "react-router-dom";

import Login from "./Components/Login";
import Dashboard from "./Components/Dashboard";
import ChangePassword from "./Components/ChangePassword";
import ResetPassword from "./Components/ResetPassword";
import Homepage from "./Components/Homepage";
import BookAppointment from "./Components/BookAppointment";
import AppointmentDetails from "./Components/HomepageComponents/AppointmentDetails";
import ViewAppointments from "./Components/ViewAppointments";


function Root(){

    return (
        <div>
            <Routes>
                <Route path="/login" element={<Login/>}></Route>
                <Route index element={<Login/>}></Route>
                <Route path="/dashboard" element={<Dashboard/>}></Route>
                <Route path="/patient/changePassword" element={<ChangePassword/>}></Route>
                <Route path="/doctor/changePassword" element={<ChangePassword/>}></Route>
                <Route path="/resetPatientPassword/" element={<ResetPassword/>}></Route>
                <Route path="/patientHomepage" element={<Homepage/>}></Route>
                <Route path="/bookAppointment" element={<BookAppointment/>}></Route>
                <Route path="/appointmentDetails/:appointmentId" element={<AppointmentDetails/>}></Route>
                <Route path="/appointments/fetchUserAppointments/:patientId" element={<ViewAppointments/>}></Route>
            </Routes>
        </div>
    )
};

function App() {
    return (
        <div className="App">
            <BrowserRouter>
                <Root/>
            </BrowserRouter>
        </div>
    );
}
export default App;