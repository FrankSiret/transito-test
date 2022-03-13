import React, { useState, useEffect } from 'react';
import { Link, NavLink, useLocation } from 'react-router-dom';
import { Avatar, Layout, Menu } from 'antd';
import { HomeOutlined, OrderedListOutlined, PlayCircleOutlined } from '@ant-design/icons';

import './menu.scss';

const { Header } = Layout;

const AppMenu = () => {
  const [selectedKeys, setSelectedKeys] = useState<string[]>([]);
  const location = useLocation();

  useEffect(() => {
    const l = location.pathname.split('/');
    if (location.pathname === '' || location.pathname === '/') setSelectedKeys(['home']);
    else if (l.length > 1 && l[1] === 'temario') setSelectedKeys(['temario']);
    else if (l.length > 1 && l[1] === 'test') setSelectedKeys(['test']);
  }, [location.pathname]);

  return (
    <Header className="app-header">
      <div className="logo">
        <Link to="/">
          <Avatar className="logo-avatar">T</Avatar>
        </Link>
      </div>
      <Menu theme="dark" mode="horizontal" selectedKeys={selectedKeys} className="menu">
        <Menu.Item key="home" icon={<HomeOutlined />}>
          <NavLink to="/">Home</NavLink>
        </Menu.Item>
        <Menu.Item key="temario" icon={<OrderedListOutlined />}>
          <NavLink to="/temario">Questions</NavLink>
        </Menu.Item>
        <Menu.Item key="test" icon={<PlayCircleOutlined />}>
          <NavLink to="/test">Test</NavLink>
        </Menu.Item>
      </Menu>
    </Header>
  );
};

export default AppMenu;
