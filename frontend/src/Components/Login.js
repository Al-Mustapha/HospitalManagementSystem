import { Button, Checkbox, Form, Input } from 'antd';
import {login, doctorsSize} from './MyClient'
import {useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";

const onFinish = (values) => {
    console.log('Success:', values);
};
const onFinishFailed = (errorInfo) => {
    console.log('Failed:', errorInfo);
};

const Login = () => {


    const navigate = useNavigate();

    const [loginDetails, setLoginDetails] = useState({
        username:"",
        password:""
    });

    const [responseStatus, setResponseStatus] = useState(false);


    const authenticate = () =>{
        console.log(loginDetails)
        login(loginDetails.username, loginDetails.password)
            .then(response => {
                document.cookie = `myCookie=${response.data};Secure;SameSite=Strict;`;
                if (response.status==200)
                    setResponseStatus(true);
            })
            .then(error => console.log(error));
    }

    const resetPassword = () => {
        navigate("/resetPatientPassword");
    }

    if (responseStatus) {
        navigate("/patientHomepage");
    }

    return(
        <>
            <Form
                name="basic"
                labelCol={{
                    span: 8,
                }}
                wrapperCol={{
                    span: 16,
                }}
                style={{
                    maxWidth: 600,
                }}
                initialValues={{
                    remember: false,
                }}
                onFinish={onFinish}
                onFinishFailed={onFinishFailed}
                autoComplete="off"
            >
                <Form.Item
                    label="Username"
                    htmlFor="username"
                    name="username"
                    rules={[
                        {
                            required: true,
                            message: 'Please input your username!',
                        },
                    ]}
                >
                    <Input name="username"
                    value ={loginDetails.username}
                           onChange={(e) => setLoginDetails({
                               ...loginDetails, [e.target.name]:e.target.value
                           })}
                    />
                </Form.Item>

                <Form.Item
                    label="Password"
                    htmlFor="password"
                    name="password"
                    rules={[
                        {
                            required: true,
                            message: 'Please input your password!',
                        },
                    ]}
                >
                    <Input.Password
                        name="password"
                        value ={loginDetails.password}
                        onChange={(e) => setLoginDetails({
                            ...loginDetails, [e.target.name]:e.target.value
                        })}

                    />
                </Form.Item>

                <Form.Item
                    name="remember"
                    valuePropName="checked"
                    wrapperCol={{
                        offset: 8,
                        span: 16,
                    }}
                >
                    <Checkbox>Remember me</Checkbox>
                </Form.Item>

                <Form.Item
                    wrapperCol={{
                        offset: 8,
                        span: 16,
                    }}
                >
                    <Button type="primary" htmlType="submit"
                    onClick={authenticate}
                    >
                        Submit
                    </Button>
                </Form.Item>
            </Form>

            <a onClick={resetPassword}>Forgotten Password? Click here</a>
        </>
    )

}


export default Login;