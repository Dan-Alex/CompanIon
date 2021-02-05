import usersAPI from "../../resource/api/users";
import filesAPI from "../../resource/api/files";
import response from "vue-resource/src/http/response";

export default {

    state: {
        files: []
    },

    getters: {
        getFiles: state => state.files
    },

    mutations: {

        getFilesMutation(state, files){
            state.files = files;
        },

        addFileMutation(state, file){
            state.files =[
                ...state.files,
                file
            ]
        }
    },

    actions:{
        async getFilesAction({commit}, user) {
            const result = await usersAPI.getFiles(user);
            const data = await result.json();
            if (result.ok){
                commit('getFilesMutation', data);
            }
        },

        async addFileAction({commit}, body) {
            const formData= new FormData();
            formData.append('file', body.file);
            const result1 = await filesAPI.add(formData);
            const savedFile = await result1.json();
            const result2 = await usersAPI.addFile(savedFile, body.user);
            const data = await result2.json();
            if (result2.ok){
                commit('addFileMutation', data)
            }
            }
    }
}