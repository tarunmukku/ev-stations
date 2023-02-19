import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';
import { useSearchParams } from 'react-router-dom';
class StationList extends Component {

    constructor(props) {
        super(props);
        this.state = {stations: []};
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {
       
        fetch('http://localhost:8080/stations'+this.props.location.search)
            .then(response => response.json())
            .then(data => this.setState({stations: data}));
    }

    async remove(id) {
        await fetch(`/stations/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedStations = [...this.state.stations].filter(i => i.id !== id);
            this.setState({stations: updatedStations});
        });
    }

    render() {
        const {stations} = this.state;

        const stationList = stations.map(station => {
            return <tr key={station.id}>
                <td style={{whiteSpace: 'nowrap'}}>{station.name}</td>
                <td>{station.address}</td>
                <td>{station.price}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={"/stations/" + station.id}>Edit</Button>
                        <Button size="sm" color="danger" onClick={() => this.remove(station.id)}>Delete</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });

        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/stations/new">Add Station</Button>
                    </div>
                    <h3>Stations</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="30%">Name</th>
                            <th width="30%">Address</th>
                            <th width="30%">Price</th>
                            <th width="40%">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {stationList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}

export default StationList;