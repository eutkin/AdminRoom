import React, {Component} from 'react';
import {Button, ButtonGroup, Container, Table} from 'reactstrap';
import AppNavbar from './AppNavbar';
import {Link} from 'react-router-dom';

class Redirects extends Component {
    constructor(props) {
        super(props);
        this.state = {redirects: [], isLoading: true};
        this.remove = this.remove.bind(this);
        this.defaultDestAlwaysFirstComparator = this.defaultDestAlwaysFirstComparator.bind(this);
    }

    componentDidMount() {
        this.setState({isLoading: true});
        fetch('/api/redirect')
            .then(response => response.json())
            .then(data => this.setState({redirects: data, isLoading: false}))
    }

    defaultDestAlwaysFirstComparator(destination1, destination2) {
        if (destination1.byDefault) {
            return -1;
        } else if (destination2.byDefault) {
            return 1;
        }
        return 0;
    }

    async remove(id) {
        await fetch(`/api/redirect/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedRedirects = [...this.state.redirects].filter(redirect => redirect.path !== id);
            this.setState({redirects: updatedRedirects})
        });
    }

    render() {
        const {redirects, isLoading} = this.state;

        if (isLoading) {
            return <p>Loading...</p>
        }

        const redirectList = redirects.map(redirect => {
            const blackList = redirect.blackList.map(bl => <li key={bl}>{bl}</li>);
            const destinations = redirect.destinations
                .sort(this.defaultDestAlwaysFirstComparator)
                .map(destination => <li key={destination.id}>{destination.url}</li>);
            return <tr key={redirect.path}>
                <td>{redirect.path}</td>
                <td>{redirect.type}</td>
                <td>{redirect.random ? "Да" : "Нет"}</td>
                <td>
                    <ul>
                        {blackList}
                    </ul>
                </td>
                <td>
                    {redirect.blackUrl}
                </td>
                <td>
                    <ul>
                        {destinations}
                    </ul>
                </td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={`/redirects/${redirect.path}`}>Edit</Button>
                        <Button size="sm" color="danger" onClick={() => this.remove(redirect.path)}>Delete</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });

        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/redirects/new">Добавить Редирект</Button>
                    </div>
                    <h3>Current Redirect</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th>Адрес</th>
                            <th>Тип</th>
                            <th>Случайный выбор</th>
                            <th>Черный список</th>
                            <th>Адрес для черного списка</th>
                            <th>Направления</th>
                            <th>Действия</th>
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
