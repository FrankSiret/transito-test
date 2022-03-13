import 'react-toastify/dist/ReactToastify.css';
import './app.scss';
import 'app/config/dayjs.ts';

import React from 'react';
import { Layout } from 'antd';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import { ToastContainer, toast } from 'react-toastify';

import ErrorBoundary from 'app/shared/error/error-boundary';
import AppRoutes from 'app/routes';
import AppMenu from './shared/layout/menu';

const baseHref = document.querySelector('base').getAttribute('href').replace(/\/$/, '');

const { Content, Footer } = Layout;

export const App = () => {
  return (
    <Router basename={baseHref}>
      <Layout className="app">
        <ToastContainer position={toast.POSITION.BOTTOM_LEFT} className="toastify-container" toastClassName="toastify-toast" />
        <AppMenu />
        <Content className="app-page">
          <div className="site-layout-content">
            <Switch>
              <Route path="/">
                <ErrorBoundary>
                  <AppRoutes />
                </ErrorBoundary>
              </Route>
            </Switch>
          </div>
        </Content>
        <Footer className="app-footer">
          Transito ©2022 Created by{' '}
          <a href="https://github.com/FrankSiret" rel="noreferrer" target="_blank">
            FrankSiret
          </a>
        </Footer>
      </Layout>
    </Router>
  );
};

export default App;
