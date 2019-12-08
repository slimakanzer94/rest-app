var personApi = Vue.resource('/person{/id}');

Vue.component('person-row', {
    props: ['person', 'editMethod', 'persons'],
    template: '<div>' +
                '<table>' +
                    '<tr>' +
                        '<th>Id</th>' +
                        '<th>Name</th>' +
                        '<th>Last Name</th>' +
                        '<th>Birth Date</th>' +
                    '</tr>' +
                    '<td>' +
                        '<td>{{person.id}}</td>' +
                        '<td>{{person.name}}</td>' +
                        '<td>{{person.lastName}}</td>' +
                        '<td>{{person.birthDate}}</td>' +
                        '<td>' +
                            '<span>' +
                                '<input type="button" value="Edit" @click="edit">' +
                            '</span>' +
                            '<span>' +
                                '<input type="button" value="X" @click="del">' +
                            '</span>' +
                        '</td>' +
                    '</tr>' +
                '</table>' +
              '</div>',
    methods: {
        edit: function () {
            this.editMethod(this.person);
        },
        del: function () {
            personApi.remove({id: this.person.id}).then(result => {
                if (result.ok) {
                    this.persons.splice(this.persons.indexOf(this.person), 1);
                }
            })
        }
    }
});

Vue.component('person-form', {
    props: ['persons', 'personAttr'],
    data: function() {
        return {
            id: '',
            name: '',
            lastName: '',
            birthDate: ''
        }
    },
    watch: {
        personAttr: function (newValue, oldValue) {
            this.id = newValue.id;
            this.name = newValue.name;
            this.lastName = newValue.lastName;
            this.birthDate = newValue.birthDate;
        }
    },
    template: '<div>' +
                    '<input type="text" placeholder="Write name" v-model="name">' +
                    '<input type="text" placeholder="Write lastname" v-model="lastName">' +
                    '<input type="text" placeholder="Write birth date" v-model="birthDate">' +
                    '<input type="button" value="Save" @click="save">' +
              '</div>',
    methods: {
        save: function () {
                    if(this.id) {
                        var person = {id: this.id, name: this.name, lastName: this.lastName, birthDate: this.birthDate};
                        personApi.update({id: this.id}, person).then(result =>
                    result.json().then(data => {
                        var index = getIndex(this.persons, data.id);
                        this.persons.splice(index, 1, data);
                        })
                    )
                    } else {
                        var person = {name: this.name, lastName: this.lastName, birthDate: this.birthDate};
                        personApi.save({}, person).then(result =>
                        result.json().then(data => {
                            this.persons.push(data);
                    })
                    )
                    }
            this.id='';
            this.name = '';
            this.lastName = '';
            this.birthDate = '';
        }
    }
});

Vue.component('person-list', {
    props: ['persons'],
    data: function() {
        return {
            person: null
        }
    },
    template: '<div style="position: relative; width: 300px">' +
        '<person-form :persons="persons" :personAttr="person"/>' +
        '<person-row v-for="person in persons" :key="person.id" :person="person" :editMethod="editMethod" :persons="persons">{{person.name}}</person-row>' +
        '</div>',
    created: function () {
        personApi.get().then(result =>
        result.json().then(data =>
        data.forEach(person => this.persons.push(person)))
    )
    },
    methods: {
        editMethod: function (person) {
            this.person = person
        }
    }
});

var app = new Vue({
    el: '#app',
    template: '<person-list :persons="persons"/>',
    data: {
        persons: []
    }
});

function getIndex(list, id) {
    for (var i = 0; i < list.length; i++) {
        if (list[i].id === id) {
            return i;
        }
    }
    return -1;
}