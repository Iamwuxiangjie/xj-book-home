import {get,post} from './base.js'


const emulateJSON_option = {
  emulateJSON: true
}

export const doLogin=({username,password})=>post({url:'/login',postBody:{username,password},options:emulateJSON_option})

export const doTestAdmin=()=>get({url:'/web/admin/testAdmin'})

export const doTestUser=()=>get({url:'/testUser'})

