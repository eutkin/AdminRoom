import React, { Component } from 'react';
import { Button, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';

class Redirects extends Component {
  constructor(props) {
    super(props);
    this.state = {redirects: [], isLoading: true};
    this.remove = this.remove.bind(this);
  }

  componentDidMount() {
    this.setState({isLoading: true});
    fetch('/api/redirect')
    .then(response => response.json())
    .then(data => this.setState({redirects: data, isLoading: false}))
  }

  async remove(id) {
    await fetch('/api/redirect/${id}', {
      method: 'DELETE',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(() => {
      let updatedRedirects = [...this.state.redirects].filter(i => i.id !== id);
      this.setState({redirects : updatedRedirects})
    });
  }

  render() {
    const {redirects, isLoading} = this.state;

    if (isLoading) {
      return <p>Loading...</p>
    }

    const redirectList = redirects.map(redirect => {
      return <tr key={redirect.path}>
        <td>{redirect.path}</td>
        <td>{redirect.type}</td>
        <td>{redirect.random}</td>
      </tr>
    });

    return (
        <div>
          <AppNavbar/>
          <Container fluid>
            <div className="float-right">
              <Button color="success" tag={Link} to="/redirect">Add Group</Button>
            </div>
            <h3>Current Redirect</h3>
            <Table className="mt-4">
              <thead>
              <tr>
                <th width="20%">Адрес</th>
                <th width="20%">Тип</th>
                <th width="10%">Случайный выбор</th>
              </tr>
              </thead>
              <tbody>
              {redirectList}
              </tbody>
            </Table>
          </Container>
        </div>
    );
  }
}

export default Redirects;
