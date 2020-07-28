import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';

class App extends Component {
  state = {
    isLoading: true,
    bookmarks: []
  }

  async componentDidMount() {
    const response = await fetch('/api/bookmark');
    const body = await response.json();
    this.setState({
      bookmarks: body,
      isLoading: false
    });
  }




  render() {
    const { bookmarks, isLoading } = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <div className="App-intro">
            <h2>Crossmarks List</h2>
            {bookmarks.map(bookmark =>
              <div key={bookmark.id}>
                {bookmark.name}
              </div>
            )}
          </div>
        </header>
      </div>
    );
  }
}

export default App;
