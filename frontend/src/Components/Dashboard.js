

import {
    DesktopOutlined, EditFilled,
    FileOutlined, HeartFilled, HeartOutlined, MedicineBoxFilled, MedicineBoxOutlined,
    PieChartOutlined, SafetyCertificateFilled,
    TeamOutlined,
    UserOutlined,
} from '@ant-design/icons';
import { Breadcrumb, Layout, Menu, theme } from 'antd';
import {useEffect, useState} from "react";
import {BiDonateBlood, BiInjection} from "react-icons/bi";
import {GiBlood, GiMedicinePills} from "react-icons/gi";
import {MdBloodtype} from "react-icons/md";
import {IoBedOutline} from "react-icons/io5";
import {SiConsul, SiMeetup} from "react-icons/si";
import {CgPassword} from "react-icons/cg";
import {BsPassport} from "react-icons/bs";
import {doctorsSize} from "./MyClient";
import {FaUserDoctor} from "react-icons/fa6";
const { Header, Content, Footer, Sider } = Layout;
function getItem(label, key, icon, children) {
    return {
        key,
        icon,
        children,
        label,
    };
}
const items = [

    getItem('Dashboard', '1', <DesktopOutlined />),
    getItem('User', 'sub1', <UserOutlined />, [
        getItem('Update Profile', '3',<EditFilled/>),
        getItem("Update Passport", '15', <BsPassport/>),
        getItem('Change Password', '5', <CgPassword/>),
    ]),
    getItem('Team', 'sub2', <TeamOutlined />,
        [
            getItem('Doctors', '6', <MedicineBoxFilled/>),
            getItem('Nurses', '8', <BiInjection/>),
            getItem('Pharmacists', '9', <GiMedicinePills/>),
            getItem('Laboratorists', '10', <MdBloodtype/>),
            getItem('Patients', '16', <UserOutlined/>),
            getItem('Appointments', '17', <SiMeetup/>),
            getItem('Drugs', '18', <GiMedicinePills/>),
        ]),

    getItem('Option 2', '2', <DesktopOutlined />),


    getItem('Files', '11', <FileOutlined />),
];

function tellClicked() {
    console.log("I was clicked")
}

const Dashboard = () => {

    const [doctorCount, setDoctorCount] = useState(0)

    const noOfDoctors = () => {
        doctorsSize()
            .then(response => setDoctorCount(response.data))
            .then(error => console.log(error));
    }

    useEffect(()=> {
        noOfDoctors();
    },[])



    const [collapsed, setCollapsed] = useState(false);
    const {
        token: { colorBgContainer },
    } = theme.useToken();
    return(
        <>
            <Layout
                style={{
                    minHeight: '100vh',
                }}
            >
                <Sider collapsible collapsed={collapsed} onCollapse={(value) => setCollapsed(value)}>
                    <div className="demo-logo-vertical" />
                    <img style={{width:'100px',height:'100px', backgroundColor:'white', borderRadius:'100px'}}></img>
                    <Menu theme="dark" defaultSelectedKeys={['1']} mode="inline" items={items} />
                </Sider>
                <Layout>
                    <Header
                        style={{
                            padding: 0,
                            background: colorBgContainer,
                        }}
                    />
                    <Content
                        style={{
                            margin: '0 16px',
                        }}
                    >
                        <Breadcrumb
                            style={{
                                margin: '16px 0',
                            }}
                        >


                        </Breadcrumb>


                        <div
                            style={{
                                padding: 24,
                                minHeight: 360,
                                background: "lightgray",
                            }}
                        >
                            <div>
                            <div style={{display:'flex'}}>
                            <div style={{width:'330px',height:'150px', backgroundColor:'white', margin:'10px',  overflow:'hidden'}}>
                                <FaUserDoctor style={{fontSize:'120px', marginTop:'50px', color:'antiquewhite'}}/>
                                <div style={{float:'left',marginLeft:'30px', textAlign:'left',}}>
                                <p>{doctorCount}</p>
                                <p style={{fontSize:'28px'}}>Doctors</p>
                                </div>

                            </div>
                            <div style={{width:'330px',height:'150px', backgroundColor:'white', margin:'10px', overflow:'hidden'}}>
                                <BiInjection style={{fontSize:'120px', marginTop:'50px', color:'antiquewhite'}}/>
                                <div style={{float:'left',marginLeft:'30px', textAlign:'left'}}>
                                <p>Fetch no from db</p>
                                <p style={{fontSize:'28px'}}>Nurses</p>
                                    </div>
                            </div>


                            <div style={{width:'330px',height:'150px', backgroundColor:'white', margin:'10px', overflow:'hidden'}}>
                                <GiMedicinePills style={{fontSize:'120px', marginTop:'50px', color:'antiquewhite'}}/>
                                <div style={{float:'left',marginLeft:'30px', textAlign:'left'}}>
                                    <p>Fetch no from db</p>
                                    <p style={{fontSize:'28px'}}>Pharmacists</p>
                                </div>
                            </div>

                            </div>

                                <div style={{display:'flex'}}>
                                <div style={{width:'330px',height:'150px', backgroundColor:'white', margin:'10px', overflow:'hidden'}}>
                                    <MdBloodtype style={{fontSize:'120px', marginTop:'50px', color:'antiquewhite'}}/>
                                    <div style={{float:'left',marginLeft:'30px', textAlign:'left'}}>
                                        <p>Fetch no from db</p>
                                        <p style={{fontSize:'28px'}}>Laboratorists</p>
                                    </div>
                            </div>
                            <div style={{width:'330px',height:'150px', backgroundColor:'white', margin:'10px', overflow:'hidden'}}>
                                <UserOutlined style={{fontSize:'120px', marginTop:'50px', color:'antiquewhite'}}/>
                                <div style={{float:'left',marginLeft:'30px', textAlign:'left'}}>
                                    <p>Fetch no from db</p>
                                    <p style={{fontSize:'28px'}}>Patients</p>
                                </div>
                            </div>

                                    <div style={{width:'330px',height:'150px', backgroundColor:'white', margin:'10px',
                                        padding:'0px', overflow:'hidden'
                                    }}>
                                        <SiMeetup style={{fontSize:'120px', marginTop:'50px', color:'antiquewhite', marginRight:'0px'}}/>
                                        <div style={{float:'left',marginLeft:'30px', textAlign:'left'}}>
                                            <p>Fetch no from db</p>
                                            <p style={{fontSize:'28px'}}>Appointments</p>
                                        </div>
                                    </div>


                                </div>

                                <div style={{display:'flex'}}>
                            <div style={{width:'330px',height:'150px', backgroundColor:'white', margin:'10px',
                                padding:'0px', overflow:'hidden'
                            }}>
                                <GiMedicinePills style={{fontSize:'120px', marginTop:'50px', color:'antiquewhite'}}/>
                                <div style={{float:'left',marginLeft:'30px', textAlign:'left'}}>
                                    <p>Fetch no from db</p>
                                    <p style={{fontSize:'30px'}}>Drugs</p>
                                </div>
                            </div>

                                </div>
                            </div>

                        </div>
                    </Content>
                    <Footer
                        style={{
                            textAlign: 'center',
                        }}
                    >
                        Developed by Alfa
                    </Footer>
                </Layout>
            </Layout>


        </>

    )
}
export default Dashboard;
