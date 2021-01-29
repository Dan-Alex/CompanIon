export default {
    state:{
        myProfile: profile
    },
    getters: {
        getProfile: state => state.myProfile,
    }
}