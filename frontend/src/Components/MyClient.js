import axios from "axios";
import {useParams} from "react-router-dom";
import Cookies from 'js-cookie'

const token = Cookies.get("myCookie");

export const extractClaimsFromToken = () =>
    axios.get("http://localhost:8082/user/extractClaims/" + token);

export const login = (username, password) =>
    axios.post("http://localhost:8082/user/authenticate", {
        username: username,
        password: password
    });

export const doctorsSize = () =>
    axios.get("http://localhost:8082/doctor/size");

export const patientChangePassword = (token, pss1, pss2) =>
    axios.post("http://localhost:8082/patient/changePassword?token="+token, {
        newPassword: pss1,
        reEnterNewPassword:pss2
    });

export const doctorChangePassword = (token, pss1, pss2) =>
    axios.post("http://localhost:8082/doctor/changePassword?token="+token, {
        newPassword: pss1,
        reEnterNewPassword:pss2
    });

export const ResetPatientPassword = (email) =>
    axios.post("http://localhost:8082/patient/resetPassword/" + email);

export const ResetDoctorPassword = (email) =>
    axios.post("http://localhost:8082/doctor/resetPassword/" + email);


export const signup = () =>
    axios.post("");

export const createAppointment = (appointmentDate, appointmentTime) =>
    axios.post("http://localhost:8082/appointment/createAppointment", {
        appointmentDate: appointmentDate,
        appointmentTime: appointmentTime
    },{
        headers: {
            Authorization: "Bearer " + token
        }
    });

export const viewAppointmentDetails = (id) =>
    fetch("http://localhost:8082/appointment/viewUserAppointment/" + id, {
        headers: {
            Authorization: "Bearer " + token
        }
    });

export const initiatePayment = (id) =>
    axios.post("http://localhost:8082/appointment/pay/" + id,{
        id: id
        },
        {
            headers: {
                Authorization: "Bearer " + token
            }
        });

export const fetchAllAppointmentsByUser = (patientId) =>
    axios.get("http://localhost:8082/appointment/fetchUserAppointments/" + patientId,{
        headers: {
            Authorization: "Bearer " + token
        }
    });


