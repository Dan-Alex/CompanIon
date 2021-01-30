export default {
    state:{
        profile: profile,
        roles: roles
    },
    getters: {
        getProfile: state => state.profile,
        getRoles: state => state.roles
    }
}