import React, {Component} from 'react';
import AppNavbar from "./AppNavbar";
import {Button, Container, Form, FormGroup, Input, Label} from 'reactstrap';
import {Link, withRouter} from "react-router-dom";

class RedirectEdit extends Component {
    emptyRedirect = {
        path: '',
        type: '',
        random: '',
        blackList: [],
        blackUrl: '',
        destinations: [],
        isNew: true
    };

    constructor(props) {
        super(props);
        this.state = {
            item: this.emptyRedirect
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChangeTextArea = this.handleChangeTextArea.bind(this);
    }

    async componentDidMount() {
        if (this.props.match.params.path !== 'new') {
            let redirect = await (await fetch(`/api/redirect/${this.props.match.params.path}`)).json();
            redirect.isNew = false;
            this.setState({item: redirect})
        }
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let item = {...this.state.item};
        item[name] = value;
        this.setState({item})
    }

    handleChangeTextArea(event) {
        const target = event.target;
        const list = target.value.split('\n').map(line => line.trim()).filter(line => line !== '');
        const name = target.name;
        let item = {...this.state.item};
        item[name] = list;
        this.setState({item})
    }

    async handleSubmit(event) {
        event.preventDefault();
        const {item} = this.state;
        await fetch('/api/redirect', {
            method: item.isNew ? 'POST' : 'PUT',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(item),
        });
        this.props.history.push('/redirects');
    }

    render() {
        const {item} = this.state;
        const title = <h2>{item.isNew ? 'Создать Редирект' : 'Редактировать Редирект'}</h2>;
        const pathFormGroup = this.getPathFormGroup(item);
        return <div>
            <AppNavbar/>
            <Container fluid>
                {title}
                <Form onSubmit={this.handleSubmit}>
                    {pathFormGroup}
                    <FormGroup>
                        <Label for="type">Тип редиректа</Label>
                        <Input type="select" name="type" id="type"
                               onChange={this.handleChange}
                        >
                            <option value="META">META</option>
                            <option value="STATUS_307">307</option>
                        </Input>
                    </FormGroup>
                    <FormGroup>
                        <Label for="random">Случайный выбор между направлениями</Label>≈
                        <Input type="checkbox" name="random" id="random" onChange={this.handleChange}/>{' '}
                    </FormGroup>
                    <FormGroup>
                        <Label for="blackUrl">Адрес для черного списка</Label>
                        <Input type="text" name="blackUrl" id="blackUrl" value={item.blackUrl || ''}
                               onChange={this.handleChange} autoComplete="blackUrl"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="blackList">Черный список ip</Label>
                        <Input type="textarea" name="blackList" id="blackList" onChange={this.handleChangeTextArea}/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="destinations">Черный список ip</Label>
                        <Input type="textarea" name="destinations" id="destinations" onChange={this.handleChangeTextArea}/>
                    </FormGroup>
                    <FormGroup>
                        <Button color="primary" type="submit">Save</Button>{' '}
                        <Button color="secondary" tag={Link} to="/redirects">Cancel</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>
    }

    getPathFormGroup(item) {
        return item.isNew ?
            <FormGroup>
                <Label for="path">Путь</Label>
                <Input type="text" name="path" id="path" value={item.path || ''}
                       onChange={this.handleChange} autoComplete="path"/>
            </FormGroup>
            : '';
    }
}

export default withRouter(RedirectEdit);
