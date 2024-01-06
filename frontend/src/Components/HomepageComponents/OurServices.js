import {Carousel} from "antd";
import {FaAmbulance, FaWheelchair, FaXRay} from "react-icons/fa";
import {MdLocalHospital} from "react-icons/md";
import {GiBabyFace, GiTestTubes} from "react-icons/gi";
import {IoIosWoman, IoMdHappy, IoMdMedkit} from "react-icons/io";

const contentStyle = {
    height: '500px',
    color: '#fff',
    // lineHeight: '160px',
    textAlign: 'center',
    background: '#364d79',
    alignItems:'center',
    alignContent:'center',
    justifyContent:'center',
};

const iconsStyle = {
    backgroundColor:'#364d79',
    color:'white',
    width:'330px',
    height:'330px'
}

const OurServices = () => {
    return(
        <>
            <h1>Our Services</h1>
            <hr style={{width:'100px', borderColor:'blue'}}></hr>



            <Carousel autoplay>

                <div>

                    <div style={contentStyle}>

                        <div style={{display:'flex', alignItems:'center', justifyContent:'center'}}>
                            <div style={{margin:'40px'}}>
                                <FaAmbulance style={iconsStyle}/><br/>
                                <font size='6'>Emergency Care</font>
                            </div>
                            <div style={{margin:'40px'}}>
                                <MdLocalHospital style={iconsStyle}/><br/>
                                <font size='6'>Surgery</font>
                            </div>
                            <div style={{margin:'40px'}}>
                                <GiTestTubes style={iconsStyle}/><br/>
                                <font size='6'>Laboratory Services</font>
                            </div>

                        </div>

                    </div>

                </div>

                <div>
                    <div style={contentStyle}>

                        <div style={{display:'flex', alignItems:'center', justifyContent:'center'}}>
                            <div style={{margin:'40px'}}>
                                <FaXRay  style={iconsStyle}/><br/>
                                <font size='6'>Radiology</font>
                            </div>
                            <div style={{margin:'40px'}}>
                                <IoMdMedkit style={iconsStyle}/><br/>
                                <font size='6'>Pharmacy</font>
                            </div>
                            <div style={{margin:'40px'}}>
                                <IoIosWoman  style={iconsStyle}/><br/>
                                <font size='6'>Maternity Care</font>
                            </div>

                        </div>

                    </div>

                </div>

                <div>
                    <div style={contentStyle}>

                        <div style={{display:'flex', alignItems:'center', justifyContent:'center'}}>
                            <div style={{margin:'40px'}}>
                                <GiBabyFace   style={iconsStyle}/><br/>
                                <font size='6'>Pediatric Care</font>
                            </div>
                            <div style={{margin:'40px'}}>
                                <IoMdHappy  style={iconsStyle}/><br/>
                                <font size='6'>Mental Health Services</font>
                            </div>
                            <div style={{margin:'40px'}}>
                                <FaWheelchair  style={iconsStyle}/><br/>
                                <font size='6'>Physical Therapy</font>
                            </div>

                        </div>

                    </div>
                </div>
            </Carousel>
        </>
    )
}
export default OurServices;