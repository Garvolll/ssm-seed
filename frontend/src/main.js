// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue';
import VueRouter from 'vue-router'
import routes from './router';
import Element from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

Vue.use(Element);
Vue.config.productionTip = false;

const router = new VueRouter({
	routes,
	mode: 'hash',
	strict: process.env.NODE_ENV !== 'production'
	// scrollBehavior (to, from, savedPosition) {
	//     if (savedPosition) {
	// 	    return savedPosition
	// 	} else {
	// 		if (from.meta.keepAlive) {
	// 			from.meta.savedPosition = document.body.scrollTop;
	// 		}
	// 	    return { x: 0, y: to.meta.savedPosition || 0 }
	// 	}
	// }
})

new Vue({
	router
}).$mount('#app')

