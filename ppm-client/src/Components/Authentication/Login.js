import React, { Component } from "react";
import { connect } from "react-redux";
import { login } from "../../Actions/SecurityActions";
import classnames from "classnames";
import PropTypes from "prop-types";

class Login extends Component {
  constructor() {
    super();
    this.state = {
      username: "",
      password: "",
      errors: {},
    };
  }
  onChangeHandler = (event) => {
    this.setState({
      [event.target.name]: event.target.value,
    });
  };

  onSubmitHandler = (event) => {
    event.preventDefault();
    const { username, password } = this.state;
    this.props.login({ username, password });
  };
  componentWillReceiveProps(nextProps) {
    if (nextProps.errors)
      this.setState({
        errors: nextProps.errors,
      });
  }

  render() {
    const { errors } = this.state;
    return (
      <div className="login">
        <div className="container">
          <div className="row">
            <div className="col-md-8 m-auto">
              <h1 className="display-4 text-center">Login</h1>
              <form onSubmit={this.onSubmitHandler.bind(this)}>
                <div className="form-group">
                  <input
                    type="email"
                    className={classnames("form-control form-control-lg",{
                      "is-invalid":errors.username
                    })}
                    placeholder="Email Address"
                    name="username"
                    onChange={this.onChangeHandler.bind(this)}
                  />
                  {errors.username && (
                    <div className="invalid-feedback">{errors.username}</div>
                  )}
                </div>
                <div className="form-group">
                  <input
                    type="password"
                    className={classnames("form-control form-control-lg",{
                      "is-invalid":errors.password
                    })}
                    placeholder="Password"
                    name="password"
                    onChange={this.onChangeHandler.bind(this)}
                  />
                  {errors.password && (
                    <div className="invalid-feedback">{errors.password}</div>
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
Login.propTypes = {
  login: PropTypes.func.isRequired,
  security:PropTypes.object.isRequired,
  errors:PropTypes.object.isRequired
};

const mapStateToProps = (state) => {
  return {
    errors: state.errors,
    security : state.security
  };
};
export default connect(mapStateToProps, { login })(Login);
