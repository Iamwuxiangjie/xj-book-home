import Vue from 'vue'

const SERVER_ORIGIN = 'api';

const decorate_url = ({origin, url, getBody = {}, postBody = {}}) => {
  const result = {};
  if (getBody !== {}) {
    let getBodyDataList = [];
    for (let key in getBody) {
      if (key === '$state') {
        result.$state = getBody[key];
      } else {
        getBodyDataList.push(key + "=" + getBody[key])
      }
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
  const {status, body, bodyText} = res;
  return {status, body, bodyText};
}


export const get = ({origin = SERVER_ORIGIN, url, getBody}) => {
  const request = decorate_url({origin, url, getBody});
  return new Promise((resolve, reject) => {
    Vue.http.get(request.url).then(res => {
      resolve(decorate_response(res));
    }).catch(error => {
      resolve(decorate_response(error));
    })
  })
}

export const post = ({origin = SERVER_ORIGIN, url, getBody, postBody}) => {
  const request = decorate_url({origin, url, getBody, postBody});
  return new Promise((resolve, reject) => {
    Vue.http.post(request.url, postBody).then(res => {
      resolve(decorate_response(res));
    }).catch(error => {
      resolve(decorate_response(error));
    })
  })
}
