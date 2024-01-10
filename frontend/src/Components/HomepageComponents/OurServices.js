import {Carousel} from "antd";
import {FaAmbulance, FaWheelchair, FaXRay} from "react-icons/fa";
import {MdLocalHospital} from "react-icons/md";
import {GiBabyFace, GiTestTubes} from "react-icons/gi";
import {IoIosWoman, IoMdHappy, IoMdMedkit} from "react-icons/io";

const contentStyle = {
    height: '1150px',
    color: '#fff',
    // lineHeight: '160px',
    textAlign: 'center',
    // background: '#364d79',
    alignItems:'center',
    alignContent:'center',
    justifyContent:'center',
};

const iconsStyle = {
    color:'white',
    width:'70px',
    height:'70px',
    marginTop:'20px'
}

const OurServices = () => {
    return(
        <>
            <h1>Our Services</h1>
            <hr style={{width:'100px', borderColor:'blue'}}></hr>

                    <div style={contentStyle}>

                        <div style={{display:'flex', alignItems:'center', justifyContent:'center'}}>
                            <div style={{margin:'40px', backgroundColor:'#364d79', width:'350px',height:'320px'}}>
                                <FaAmbulance style={iconsStyle}/><br/>
                                <font size='5'>Emergency Care</font>
                                <p style={{padding:'5%'}}>We are available for your emergency needs and services
                                    for your emergency needs and services
                                    for your emergency needs and services
                                    for your emergency needs and services
                                    for your emergency needs and services</p>
                            </div>

                            <div style={{margin:'40px', backgroundColor:'#364d79', width:'350px',height:'320px'}}>
                                <MdLocalHospital style={iconsStyle}/><br/>
                                <font size='5'>Surgery</font>
                                <p style={{padding:'5%'}}>We are available for your emergency needs and services
                                    for your emergency needs and services
                                    for your emergency needs and services
                                    for your emergency needs and services
                                    for your emergency needs and services</p>
                            </div>

                            <div style={{margin:'40px', backgroundColor:'#364d79', width:'350px',height:'320px'}}>
                                <GiTestTubes style={iconsStyle}/><br/>
                                <font size='5'>Laboratory Services</font>
                                <p style={{padding:'5%'}}>We are available for your emergency needs and services
                                    for your emergency needs and services
                                    for your emergency needs and services
                                    for your emergency needs and services
                                    for your emergency needs and services</p>
                            </div>
                        </div>


                        <div style={{display:'flex', alignItems:'center', justifyContent:'center'}}>
                            <div style={{margin:'40px', backgroundColor:'#364d79', width:'350px',height:'320px'}}>
                                <FaXRay style={iconsStyle}/><br/>
                                <font size='5'>Radiography</font>
                                <p style={{padding:'5%'}}>We are available for your emergency needs and services
                                    for your emergency needs and services
                                    for your emergency needs and services
                                    for your emergency needs and services
                                    for your emergency needs and services</p>
                            </div>

                            <div style={{margin:'40px', backgroundColor:'#364d79', width:'350px',height:'320px'}}>
                                <IoMdMedkit style={iconsStyle}/><br/>
                                <font size='5'>Pharmacy</font>
                                <p style={{padding:'5%'}}>We are available for your emergency needs and services
                                    for your emergency needs and services
                                    for your emergency needs and services
                                    for your emergency needs and services
                                    for your emergency needs and services</p>
                            </div>

                            <div style={{margin:'40px', backgroundColor:'#364d79', width:'350px',height:'320px'}}>
                                <IoIosWoman style={iconsStyle}/><br/>
                                <font size='5'>Maternity Care</font>
                                <p style={{padding:'5%'}}>We are available for your emergency needs and services
                                    for your emergency needs and services
                                    for your emergency needs and services
                                    for your emergency needs and services
                                    for your emergency needs and services</p>
                            </div>
                        </div>

                        <div style={{display:'flex', alignItems:'center', justifyContent:'center'}}>
                            <div style={{margin:'40px', backgroundColor:'#364d79', width:'350px',height:'320px'}}>
                                <GiBabyFace style={iconsStyle}/><br/>
                                <font size='5'>Paediatric Care</font>
                                <p style={{padding:'5%'}}>We are available for your emergency needs and services
                                    for your emergency needs and services
                                    for your emergency needs and services
                                    for your emergency needs and services
                                    for your emergency needs and services</p>
                            </div>

                            <div style={{margin:'40px', backgroundColor:'#364d79', width:'350px',height:'320px'}}>
                                <IoMdHappy style={iconsStyle}/><br/>
                                <font size='5'>Mental Health Services</font>
                                <p style={{padding:'5%'}}>We are available for your emergency needs and services
                                    for your emergency needs and services
                                    for your emergency needs and services
                                    for your emergency needs and services
                                    for your emergency needs and services</p>
                            </div>

                            <div style={{margin:'40px', backgroundColor:'#364d79', width:'350px',height:'320px'}}>
                                <FaWheelchair style={iconsStyle}/><br/>
                                <font size='5'>Physical Therapy</font>
                                <p style={{padding:'5%'}}>We are available for your emergency needs and services
                                    for your emergency needs and services
                                    for your emergency needs and services
                                    for your emergency needs and services
                                    for your emergency needs and services</p>
                            </div>
                        </div>
                    </div>
        </>
    )
}
export default OurServices;