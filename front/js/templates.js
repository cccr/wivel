const noteTmpl = note => html`
                <div><a href="#" data-author="${note.createdById}">${note.createdByName}</a></div>
                <div>${note.text}</div>
        `;

const notesTmpl = notes => html`
            <div>
            ${notes.map(noteTmpl)}
            </div>
        `;