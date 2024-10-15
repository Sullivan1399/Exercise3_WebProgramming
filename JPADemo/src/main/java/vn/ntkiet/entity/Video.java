package vn.ntkiet.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "videos")
@NamedQuery(name = "Video.findAll", query = "SELECT v FROM Video v")
public class Video implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "videoId")
	private int videoId;
	
	@Column(name = "poster", columnDefinition = "NVARCHAR(500) NULL")
	private String poster;
	
	@Column(name = "title", columnDefinition = "NVARCHAR(500) NULL")
	private String title;
	
	@Column(name = "description", columnDefinition = "NVARCHAR(500) NULL")
	private String description;
	
	@Column(name = "active")
	private int active;
	
	@Column(name = "views")
	private int views;
	
	@Column(name = "videoURL", columnDefinition = "NVARCHAR(500)")
    private String videoURL;
	
	// bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name = "categoryId")
	private Category category;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	
}
