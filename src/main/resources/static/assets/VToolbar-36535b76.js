import{p as x,F as y,G as T,m as V,x as k,y as t,aC as R,aa as E,ac as H,I as D,a8 as w,v as F,aD as $,ae as G,af as J,J as K,K as U,s as X,c as h,a0 as j,aE as q,X as u,d as o,a5 as z}from"./index-a97958e7.js";const A=x({text:String,...y(),...T()},"VToolbarTitle"),L=V()({name:"VToolbarTitle",props:A(),setup(e,n){let{slots:a}=n;return k(()=>{const s=!!(a.default||a.text||e.text);return t(e.tag,{class:["v-toolbar-title",e.class],style:e.style},{default:()=>{var l;return[s&&t("div",{class:"v-toolbar-title__placeholder"},[a.text?a.text():e.text,(l=a.default)==null?void 0:l.call(a)])]}})}),{}}}),M=[null,"prominent","default","comfortable","compact"],O=x({absolute:Boolean,collapse:Boolean,color:String,density:{type:String,default:"default",validator:e=>M.includes(e)},extended:Boolean,extensionHeight:{type:[Number,String],default:48},flat:Boolean,floating:Boolean,height:{type:[Number,String],default:64},image:String,title:String,...R(),...y(),...E(),...H(),...T({tag:"header"}),...D()},"VToolbar"),W=V()({name:"VToolbar",props:O(),setup(e,n){var c;let{slots:a}=n;const{backgroundColorClasses:s,backgroundColorStyles:l}=w(F(e,"color")),{borderClasses:C}=$(e),{elevationClasses:_}=G(e),{roundedClasses:B}=J(e),{themeClasses:P}=K(e),{rtlClasses:S}=U(),i=X(!!(e.extended||(c=a.extension)!=null&&c.call(a))),d=h(()=>parseInt(Number(e.height)+(e.density==="prominent"?Number(e.height):0)-(e.density==="comfortable"?8:0)-(e.density==="compact"?16:0),10)),r=h(()=>i.value?parseInt(Number(e.extensionHeight)+(e.density==="prominent"?Number(e.extensionHeight):0)-(e.density==="comfortable"?4:0)-(e.density==="compact"?8:0),10):0);return j({VBtn:{variant:"text"}}),k(()=>{var g;const N=!!(e.title||a.title),I=!!(a.image||e.image),m=(g=a.extension)==null?void 0:g.call(a);return i.value=!!(e.extended||m),t(e.tag,{class:["v-toolbar",{"v-toolbar--absolute":e.absolute,"v-toolbar--collapse":e.collapse,"v-toolbar--flat":e.flat,"v-toolbar--floating":e.floating,[`v-toolbar--density-${e.density}`]:!0},s.value,C.value,_.value,B.value,P.value,S.value,e.class],style:[l.value,e.style]},{default:()=>[I&&t("div",{key:"image",class:"v-toolbar__image"},[a.image?t(u,{key:"image-defaults",disabled:!e.image,defaults:{VImg:{cover:!0,src:e.image}}},a.image):t(q,{key:"image-img",cover:!0,src:e.image},null)]),t(u,{defaults:{VTabs:{height:o(d.value)}}},{default:()=>{var v,b,f;return[t("div",{class:"v-toolbar__content",style:{height:o(d.value)}},[a.prepend&&t("div",{class:"v-toolbar__prepend"},[(v=a.prepend)==null?void 0:v.call(a)]),N&&t(L,{key:"title",text:e.title},{text:a.title}),(b=a.default)==null?void 0:b.call(a),a.append&&t("div",{class:"v-toolbar__append"},[(f=a.append)==null?void 0:f.call(a)])])]}}),t(u,{defaults:{VTabs:{height:o(r.value)}}},{default:()=>[t(z,null,{default:()=>[i.value&&t("div",{class:"v-toolbar__extension",style:{height:o(r.value)}},[m])]})]})]})}),{contentHeight:d,extensionHeight:r}}});export{W as V,A as a,L as b,O as m};
