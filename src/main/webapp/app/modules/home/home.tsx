import './home.scss';

import React from 'react';
import { Link } from 'react-router-dom';
import { Translate } from 'react-jhipster';
import { Row, Col, Alert } from 'reactstrap';

import { useAppSelector } from 'app/config/store';
import { Button } from 'antd';

export const Home = () => {
  return (
    <Row>
      <Col md="9">
        <h2>Welcome</h2>
        <Link to={'/test'}>
          <Button type="primary">Start test</Button>
        </Link>
      </Col>
    </Row>
  );
};

export default Home;
