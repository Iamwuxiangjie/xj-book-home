import Vue from 'vue'
import iView from 'iview'
const SERVER_ORIGIN = 'api';

const decorate_url = ({origin, url, getBody = {}, postBody = {}}) => {
  const result = {};
  if (getBody !== {}) {
    let getBodyDataList = [];
    for (let key in getBody) {
      getBodyDataList.push(key + "=" + getBody[key])
    }
    if (getBodyDataList.length > 0) {
      url += "?" + getBodyDataList.join("&");
    }
  }
  result.url = `/${origin}${url}`;
  if (postBody != {}) {
    result.post = postBody;
  }
  return result;
}

const decorate_response = (res) => {
  const {body} = res;
  return body;
}

const default_error_handler = (res)=>{
  const {body} = res;
  iView.Message.error(body.body);
  return decorate_response(res);
}

export const get = ({origin = SERVER_ORIGIN, url, getBody}) => {
  const request = decorate_url({origin, url, getBody});
  return new Promise((resolve, reject) => {
    Vue.http.get(request.url).then(res => {
      resolve(decorate_response(res));
    }).catch(error => {
      resolve(default_error_handler(error));
    })
  })
}

export const post = ({origin = SERVER_ORIGIN, url, getBody, postBody,options={}}) => {
  const request = decorate_url({origin, url, getBody, postBody});
  return new Promise((resolve, reject) => {
    Vue.http.post(request.url, postBody,options).then(res => {
      resolve(decorate_response(res));
    }).catch(error => {
      resolve(default_error_handler(error));
    })
  })
}
