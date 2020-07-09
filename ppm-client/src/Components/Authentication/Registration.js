import React, { Component } from "react";
import { registerUser } from "../../Actions/SecurityActions";
import { connect } from "react-redux";
import PropTypes from "prop-types";
import classnames from "classnames"
class Registration extends Component {
  constructor() {
    super();
    this.state = {
      username: "",
      firstName: "",
      lastName: "",
      password: "",
      confirmPassword: "",
      errors: {},
    };
    this.onChangeHandler = this.onChangeHandler.bind(this);
    this.onSubmitHandler = this.onSubmitHandler.bind(this);
  }

  onChangeHandler = (event) => {
    this.setState({
      [event.target.name]: event.target.value,
    });
  };
  

  onSubmitHandler = (event) => {
    event.preventDefault();
    const {
      username,
      firstName,
      lastName,
      password,
      confirmPassword,
    } = this.state;
   
    this.props.registerUser(
      { username, firstName, lastName, password, confirmPassword },
      this.props.history)
  };

  componentWillReceiveProps(nextProps){
      if(nextProps.errors){
          this.setState({
              errors:nextProps.errors
          })
      }
  }
  render() {
    return (
      <div className="register">
        <div className="container">
          <div className="row">
            <div className="col-md-8 m-auto">
              <h1 className="display-4 text-center">Sign Up</h1>
              <p className="lead text-center">Create your Account</p>
              <form onSubmit={this.onSubmitHandler}>
                <div className="form-group">
                  <input
                    type="text"
                    className={classnames("form-control form-control-lg",{
                        "is-invalid":this.state.errors.firstName
                    })}
                    placeholder="FirstName"
                    name="firstName"
                    onChange={this.onChangeHandler}
                  />
                  
                  {this.state.errors.firstName && (
                    <div className="invalid-feedback">{this.state.errors.firstName}</div>
                  )}
                </div>
                <div className="form-group">
                  <input
                    type="text"
                    className={classnames("form-control form-control-lg",{
                        "is-invalid":this.state.errors.lastName
                    })}
                    placeholder="Last Name"
                    name="lastName"
                    onChange={this.onChangeHandler}
                  />
                  {this.state.errors.lastName && (
                    <div className="invalid-feedback">{this.state.errors.lastName}</div>
                  )}
                </div>
                <div className="form-group">
                  <input
                    type="email"
                    className={classnames("form-control form-control-lg",{
                        "is-invalid":this.state.errors.username
                    })}
                    placeholder="Email Address"
                    name="username"
                    onChange={this.onChangeHandler}
                  />
                  {this.state.errors.username && (
                    <div className="invalid-feedback">{this.state.errors.username}</div>
                  )}
                </div>
                <div className="form-group">
                  <input
                    type="password"
                    className={classnames("form-control form-control-lg",{
                        "is-invalid":this.state.errors.password
                    })}
                    placeholder="Password"
                    name="password"
                    onChange={this.onChangeHandler}
                    
                  />
                  {this.state.errors.password && (
                    <div className="invalid-feedback">{this.state.errors.password}</div>
                  )}
                </div>
                <div className="form-group">
                  <input
                    type="password"
                    className={classnames("form-control form-control-lg",{
                        "is-invalid":this.state.errors.confirmPassword 
                    })}
                    placeholder="Confirm Password"
                    name="confirmPassword"
                    onChange={this.onChangeHandler}
                
                  />
                  {this.state.errors.confirmPassword && (
                    <div className="invalid-feedback">{this.state.errors.confirmPassword}</div>
                  )}
                </div>
                <input type="submit" className="btn btn-info btn-block mt-4" />
              </form>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

Registration.propTypes = {
  registerUser: PropTypes.func.isRequired,
  errors:PropTypes.object.isRequired
};

const mapStateToProps = state =>{
    return({
        errors:state.errors
    })
}
export default connect(mapStateToProps, { registerUser })(Registration);
