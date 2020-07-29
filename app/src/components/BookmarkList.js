import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import NavBar from './NavBar';
import { Link } from 'react-router-dom';

class BookmarkList extends Component {

  constructor(props) {
    super(props);
    this.state = {bookmarks: [], isLoading: true};
    this.remove = this.remove.bind(this);
  }

  componentDidMount() {
    this.setState({isLoading: true});

    fetch('api/bookmark')
      .then(response => response.json())
      .then(data => this.setState({bookmarks: data, isLoading: false}));
  }

  async remove(id) {
    await fetch(`/api/bookmark/${id}`, {
      method: 'DELETE',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(() => {
      let updatedbookmarks = [...this.state.bookmarks].filter(i => i.id !== id);
      this.setState({bookmarks: updatedbookmarks});
    });
  }

  render() {
    const {bookmarks, isLoading} = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    const bookmarkList = bookmarks.map(bookmark => {
      return <tr key={bookmark.id}>
        <td style={{whiteSpace: 'nowrap'}}>{bookmark.name}</td>
        <td><a href={bookmark.url}>{bookmark.url}</a></td>
        <td>{bookmark.description}</td>
        <td>
          <ButtonGroup>
            <Button size="sm" color="primary" tag={Link} to={"/bookmarks/" + bookmark.id}>Edit</Button>
            <Button size="sm" color="danger" onClick={() => this.remove(bookmark.id)}>Delete</Button>
          </ButtonGroup>
        </td>
      </tr>
    });

    return (
      <div>
        <NavBar/>
        <Container fluid>
          <div className="float-right">
            <Button color="success" tag={Link} to="/bookmarks/new">Add bookmark</Button>
          </div>
          <h3>My Bookmarks</h3>
          <Table className="mt-4">
            <thead>
            <tr>
              <th width="20%">Name</th>
              <th width="20%">URL</th>
              <th>Description</th>
              <th width="10%">Actions</th>
            </tr>
            </thead>
            <tbody>
            {bookmarkList}
            </tbody>
          </Table>
        </Container>
      </div>
    );
  }
}

export default BookmarkList;