import React, { Component } from 'react';
import './App.css';
import Home from './components/Home';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import BookmarkList from './components/BookmarkList';
import BookmarkEdit from './components/BookmarkEdit';

class App extends Component {
  render() {
    return (
      <Router>
        <Switch>
          <Route path='/' exact={true} component={Home}/>
          <Route path='/bookmarks' exact={true} component={BookmarkList}/>
          <Route path='/bookmarks/:id' component={BookmarkEdit}/> 
        </Switch>
      </Router>
    )
  }
}

export default App;
