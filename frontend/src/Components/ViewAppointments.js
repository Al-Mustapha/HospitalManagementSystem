import {Button, Table} from "antd";
import {fetchAllAppointmentsByUser, extractClaimsFromToken} from './MyClient'
import {useEffect, useState} from "react";

const ViewAppointments = () => {

    const [claims, setClaims] = useState(null);
    const [appointments, setAppointments] = useState([]);

    const [isDisabled, setIsDisabled] = useState(false);

    const [extractedClaims, setExtractClaims] = useState(
        {
        Authorization: null,
        authorities: null,
        exp: null,
        iat: null,
        Role: null,
        sub: "",
        userId: null
    }
    )

    const [patientId, setPatientId] = useState(null);

    const fetchUserClaims = () => {
        extractClaimsFromToken()
        .then((response) => {
            setClaims(response.data)
            setPatientId(response.data.userId);
        });
    }

    useEffect(() => {
        fetchUserClaims();
    },[]);

    const fetchAppointments = () => {
        fetchAllAppointmentsByUser(claims.userId)
            .then((response) => {
                setAppointments(response.data)

                {
                    appointments.map((pool, index) => {
                                if (pool.appointmentStatus == "Used"){
                                    setIsDisabled(true)
                                }
                        }
                    )
                }

            })
    }

    useEffect(()=> {
        if (patientId != null){
            fetchAppointments();
        }

    },[patientId])


    console.log(appointments)

    const columns = [{
       title:'Appointment ID',
        dataIndex:'appointmentId',
        key:'appointmentId'
    },
        {
            title:'Reference',
            dataIndex:'reference',
            key:'reference',
            render: (_, record) =>{
                return record?.appointmentPayment?.reference
            }
        },
        {
            title:'Date',
            dataIndex:'appointmentDate',
            key:'appointmentDate'
        },
        {
            title:'Time',
            dataIndex:'appointmentTime',
            key:'appointmentTime'
        },
        {
            title:'Status',
            dataIndex:'appointmentStatus',
            key:'appointmentStatus'
        },
        {
            title:'Payment Status',
            dataIndex:'paymentStatus',
            key:'paymentStatus'
        },
        {
            title:'Action',
            dataIndex:'action',
            key:'action',
            render: (text, record) => (
              <span>
                  <Button
                      disabled={isDisabled}
                      style={{backgroundColor:'green',color:'white'}}>Edit</Button> | <Button style={{backgroundColor:'red',color:'white'}}>Delete</Button>
              </span>
            ),
        }

    ]

    return(
        <>

           <h2 style={{color:'#364d79'}}>APPOINTMENTS</h2>

            <center>
                <Table
                    dataSource={appointments}
                    columns={columns} name="appointments" rowKey="appointmentId"
                    style={{width:'80%'}}
                ></Table>
            </center>

<br></br>
   <Button>CLICK TO EXTRACT</Button>

        </>
    )
}

export default ViewAppointments;