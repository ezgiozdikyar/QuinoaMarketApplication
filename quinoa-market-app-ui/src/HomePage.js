import React from "react";
import CustomNavbar from "./CustomNavbar";
import Card from 'react-bootstrap/Card';
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';
import './HomePage.css'; // Stil dosyasını import edin
function HomePage() {
    return (
        <>
            <CustomNavbar />
            <Row xs={1} md={3} className="g-3">
                {Array.from({ length: 6 }).map((_, idx) => (
                    <Col >
                        <Card>
                            <Card.Img variant="top" src="./images/iniasanta.jpg" />
                            <Card.Body>
                                <Card.Title>Card title</Card.Title>
                                <Card.Text>
                                    This is a longer card with supporting text below as a natural
                                    lead-in to additional content. This content is a little bit
                                    longer.
                                </Card.Text>
                            </Card.Body>
                        </Card>
                    </Col>
                ))}
            </Row>        </>
    );
}
export default HomePage



