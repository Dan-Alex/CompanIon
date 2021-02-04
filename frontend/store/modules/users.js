import usersApi from "../../resource/api/users";

export default {

    state:{
        users: []
    },

    getters: {
        getUsers: state => state.users,
    },

    mutations:{

        getAllUsersMutation(state, users){
            state.users = users
        },

        addUserMutation(state, user){
            state.users = [
                ...state.users,
                user
            ]
        },

        updateUserMutation(state, user){
            const updIndex = state.users.findIndex(i => i.id === user.id);
            state.users = [
                ...state.users.slice(0, updIndex),
                user,
                ...state.users.slice(updIndex+1)
            ]
        },

        deleteUserMutation(state, user){
            const delIndex = state.users.findIndex(i => i.id === user.id);
            state.users = [
                            ...state.users.slice(0, delIndex),
                            ...state.users.slice(delIndex+1)
                          ]
        }
    },

    actions: {

        async getAllUsersAction({commit}){
            const result = await usersApi.getAll();
            const data = await result.json();
            if (result.ok){
                commit('getAllUsersMutation', data)
            }
        },

        async addUserAction({commit}, user){
            const result = await usersApi.add(user);
            const data = await result.json();
            if (result.ok) {
                commit('addUserMutation', data)
            }
        },

        async updateUserAction({commit}, user){
            const result = await usersApi.update(user);
            const data = await result.json();
            commit('updateUserMutation', data)
        },

        async deleteUserAction({commit}, user){
            const result = await usersApi.delete(user);
            if (result.ok) {
                commit('deleteUserMutation', user)
            }
        }
    }
}
